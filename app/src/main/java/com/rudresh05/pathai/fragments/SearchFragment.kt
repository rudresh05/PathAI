package com.rudresh05.pathai.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rudresh05.pathai.R
import com.rudresh05.pathai.adapters.VideoAdapter
import com.rudresh05.pathai.databinding.FragmentSearchBinding
import com.rudresh05.pathai.models.MainViewModel
import com.rudresh05.pathai.ui.PlayerActivity

class SearchFragment : Fragment() {

   private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query !=null && query.isNotEmpty()){
                    binding.lodingAnimation.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.lodingAnimation.playAnimation()

                    var mainViewModel = MainViewModel()


                    val videoList = mainViewModel.searchVideos(query)
                    mainViewModel.videos.observe(viewLifecycleOwner){item ->
                        Log.d("SearchFragment", "Video List: $item")
                        var adapter = VideoAdapter(item){vid->
                            var intent= Intent(requireContext(), PlayerActivity::class.java)
                            var bundle = Bundle()
                            bundle.putString("VIDEO_ID", vid)
                            intent.putExtras(bundle)
                            startActivity(intent,bundle)
                        }
                        binding.lodingAnimation.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE

                        binding.recyclerView.adapter = adapter

                    }

                }else{
                    binding.lodingAnimation.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.lodingAnimation.pauseAnimation()
                }
                return true
            }

        })


    }


    companion object {

    }
}
