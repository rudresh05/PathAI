package com.rudresh05.pathai.api

import com.rudresh05.pathai.BuildConfig // We need this to get our secure API key.
import com.rudresh05.pathai.`models`.YoutubeSearchResponse // The data model for the API's response.
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface defines the contract for our YouTube API calls.
 * Retrofit uses this to generate the necessary networking code.
 */
interface YoutubeApiService {

    /**
     * Defines a GET request to the "search" endpoint.
     * The 'suspend' keyword means this function can be run in the background
     * using coroutines, preventing the app from freezing.
     */
    @GET("search")
    suspend fun searchVideos(
        // @Query annotations add parameters to the request URL.
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("maxResults") maxResults: Int = 25,
        // The API key is securely accessed from BuildConfig.
        @Query("key") apiKey: String = BuildConfig.YOUTUBE_API_KEY
    ): YoutubeSearchResponse // This function will return our data model object.
}