package com.rudresh05.pathai.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudresh05.pathai.repository.VideoRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = VideoRepository()

    // Videos ki list ke liye LiveData (koi badlav nahi)
    private val _videos = MutableLiveData<List<VideoModel>>()
    val videos: LiveData<List<VideoModel>> get() = _videos

    // Loading state ke liye ek naya LiveData
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    /**
     * Yeh ek naya public function hai jise MainActivity call karegi
     * jab user kuch search karega.
     */
    fun searchVideos(query: String){

        // viewModelScope ek coroutine launch karta hai, yaani yeh kaam background mein hoga.
        viewModelScope.launch {
            _isLoading.value = true // API call se pehle loading shuru karo

            // Repository se real data fetch karo
           val videoList = repository.searchVideos(query)
            _videos.value = videoList // LiveData ko naye data se update karo

            _isLoading.value = false // API call poora hone ke baad loading band karo
        }

    }
}