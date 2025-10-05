package com.rudresh05.pathai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(
            SlideModel(
                "https://www.bluekraft.in/wp-content/uploads/2025/03/what-is-ai-1.jpg",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSALsRWjeg7Gqe-nFsTKOPMM13xnSOYVQtpexc2Tmf_VYIlxazfPHtPrLiiPo_NNemcA0&usqp=CAU",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQrPtduF5CJ3mK344eTZ53hspstXFJ-iEskA&s",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://blog.cdn.cmarix.com/blog/wp-content/uploads/2021/06/8-Killer-Kotlin-Tips-For-Android-App-Developers.png",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://www.aress.com/assets/images/inner-headers/android-app-header.webp",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiEjBilP-PBEbL7NAsVh5jU2PEYPgaGhh8-g&s",
                ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4qoydCWxsFRz69Knzr-NX8B3rCaQYyEMtxg&s",
                ScaleTypes.CENTER_CROP
            )
        )

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser?.displayName
        val storageRef = FirebaseStorage.getInstance()


        binding.homeHII.text = "Hii! ${currentUser} \nWelcome to PathAI....."
        if (currentUser != null) {
            storageRef.getReference("profile_images/${auth.currentUser?.uid}").downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.doreman)
                    .into(binding.homeProfileImg)
            }


        }

        // webView Setup

        val videoId = "upU0OcE658E"
        val url = "https://www.youtube.com/embed/${videoId}"
        val webView = binding.recommandWebView
        val webSettings = webView.getSettings()
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.setJavaScriptEnabled(true);
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl(url)
    }
}