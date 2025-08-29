package com.rudresh05.pathai.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView // Naya import
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudresh05.pathai.adapters.VideoAdapter
import com.rudresh05.pathai.databinding.ActivityMainBinding
import com.rudresh05.pathai.models.VideoModel
import com.rudresh05.pathai.models.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var videoAdapter: VideoAdapter // Adapter ko bahar define karo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSearchView() // SearchView ko setup karne ke liye naya function

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
    // Shuru mein adapter khaali rahega
        val videoList: List<VideoModel> = emptyList()

        videoAdapter = VideoAdapter(videoList) { item, ->
            Toast.makeText(this, item, Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = videoAdapter
        }
    }

    // Yeh naya function hai
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Yeh tab call hota hai jab user keyboard par search button dabata hai
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    // ViewModel ko bolo ki is query ke liye videos search kare
                    mainViewModel.searchVideos(query)
                    binding.searchView.clearFocus() // Search ke baad keyboard chhupa do
                }
                return true
            }

            // Yeh tab call hota hai jab user search bar mein text change karta hai
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeViewModel() {
        // Loading state ko observe karo
        mainViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.recyclerView.visibility = View.GONE
                binding.lodingAnimation.visibility = View.VISIBLE
            } else {
                binding.lodingAnimation.visibility = View.GONE
            }
        }



        // Videos ki list ko observe karo
        mainViewModel.videos.observe(this) { videoList ->
            // Jaise hi data aaye, adapter ko update karo
            videoAdapter = VideoAdapter(videoList) { item, ->
                Toast.makeText(this, item, Toast.LENGTH_SHORT).show()
                var intent = Intent(this, PlayerActivity::class.java)
                var vId = item.toString()
                intent.putExtra("VIDEO_ID", vId)
                startActivity(intent)


            }
            // --- START of NEW CODE ---
            // Create an Intent to open PlayerActivity

            // Pass the ID of the selected video to the new activity

            // Start the PlayerActivity
            // --- END of NEW CODE ---
            binding.recyclerView.adapter = videoAdapter

            if (!videoList.isEmpty()) {
                binding.recyclerView.visibility = View.VISIBLE
            }
        }
    }
}