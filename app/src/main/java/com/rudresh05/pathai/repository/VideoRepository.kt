package com.rudresh05.pathai.repository



// This file is in our 'repository' package.


import android.util.Log // We'll use Log to check for errors in Logcat.
import com.rudresh05.pathai.api.RetrofitClient // Import our Retrofit client.
import com.rudresh05.pathai.models.VideoModel
import com.rudresh05.pathai.models.VideoItem // Import the data class from our API response.

// Import our simple Video data class.
 // Import the data class from our API response.


 // The Repository is now upgraded to fetch data from the network.
// It's the single source of truth for our app's data.

class VideoRepository {


     // This new function searches for videos using the YouTube API.
     // It's a 'suspend' function, so it will run in the background.

    suspend fun searchVideos(query: String): List<VideoModel> {
        // We use a try-catch block to handle potential network errors (like no internet).
        return try {
            // Make the actual network call using our RetrofitClient.
            val response = RetrofitClient.apiService.searchVideos(query = query)

            // We map the complex API response to our simple 'Video' data class
            // which is a good practice for clean architecture.
            response.items.map { videoItem ->
                mapToVideo(videoItem)
            }
        } catch (e: Exception) {
            // If an error occurs, we log it and return an empty list to prevent a crash.
            Log.e("VideoRepository", "Error fetching videos", e)
            emptyList()
        }
    }


    //  A private helper function to convert the API's VideoItem into our app's Video class.

    private fun mapToVideo(item: VideoItem): VideoModel {
        return VideoModel(
            id = item.id.videoId,
            title = item.snippet.title,
            thumbnailUrl = item.snippet.thumbnails.high.url,
            channelName = item.snippet.channelTitle
        )
    }
}