package com.rudresh05.pathai.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A singleton object to provide a single instance of the Retrofit client.
 * This is where we configure Retrofit for our YouTube API calls.
 */
object RetrofitClient {

    // The base URL for all YouTube Data API v3 calls.
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    /**
     * This creates the main Retrofit instance.
     * It's configured with the base URL and a converter factory.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        // GsonConverterFactory is used to parse the JSON response from the API
        // into our Kotlin data classes (YoutubeSearchResponse, etc.).
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * This creates an implementation of our API service interface.
     * We use 'lazy' so that the service is created only when it's first needed.
     */
    val apiService: YoutubeApiService by lazy {
        retrofit.create(YoutubeApiService::class.java)
    }
}