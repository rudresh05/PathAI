package com.rudresh05.pathai.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.rudresh05.pathai.R
import com.rudresh05.pathai.adapters.VideoAdapter
import com.rudresh05.pathai.databinding.FragmentSearchBinding
import com.rudresh05.pathai.models.MainViewModel
import com.rudresh05.pathai.ui.PlayerActivity
import org.json.JSONObject
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var keywords: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        loadKeywords()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    if (isEducationalQuery(query)) {
                        showToast("Success", "Educational content found!", MotionToastStyle.SUCCESS)
                        performSearch(query)
                    } else {
                        showToast("Warning", "Please search educational content only", MotionToastStyle.WARNING)
                    }
                } else {
                    showToast("Info", "Please enter a search query", MotionToastStyle.INFO)
                }
                return true
            }
        })
    }

    private fun loadKeywords() {
        try {
            val jsonString = requireContext().assets.open("keywords.json").bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("cs_keywords")

            val list = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.getString(i).lowercase())
            }
            keywords = list
            Log.d("SearchFragment", "Loaded keywords: ${keywords.size} items")
        } catch (e: Exception) {
            e.printStackTrace()
            keywords = listOf()
        }
    }

    private fun isEducationalQuery(query: String): Boolean {
        val lower = query.lowercase().replace(Regex("[^a-z0-9 ]"), " ")
        val cleanQuery = lower.trim()

        for (keyword in keywords) {
            if (cleanQuery.contains(keyword)) {
                Log.d("SearchCheck", "✅ Matched keyword: $keyword")
                return true
            }
        }

        Log.d("SearchCheck", "🚫 No educational keyword found in: $cleanQuery")
        return false
    }

    private fun showToast(title: String, message: String, style: MotionToastStyle) {
        MotionToast.createToast(
            requireContext() as Activity,
            title,
            message,
            style,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(requireContext(), R.font.helvetica_regular)
        )
    }

    private fun performSearch(query: String) {
        binding.lodingAnimation.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.lodingAnimation.playAnimation()

        val mainViewModel = MainViewModel()
        val videoList = mainViewModel.searchVideos(query)
        mainViewModel.videos.observe(viewLifecycleOwner) { item ->
            Log.d("SearchFragment", "Video List: $item")
            val adapter = VideoAdapter(item) { vid ->
                val intent = Intent(requireContext(), PlayerActivity::class.java)
                val bundle = Bundle()
                bundle.putString("VIDEO_ID", vid)
                intent.putExtras(bundle)
                startActivity(intent, bundle)
            }

            binding.lodingAnimation.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            binding.recyclerView.adapter = adapter
        }
    }
}
