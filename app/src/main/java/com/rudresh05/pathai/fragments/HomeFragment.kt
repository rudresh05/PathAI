package com.rudresh05.pathai.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.rudresh05.pathai.R
import com.rudresh05.pathai.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


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

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(SlideModel("https://www.bluekraft.in/wp-content/uploads/2025/03/what-is-ai-1.jpg", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSALsRWjeg7Gqe-nFsTKOPMM13xnSOYVQtpexc2Tmf_VYIlxazfPHtPrLiiPo_NNemcA0&usqp=CAU", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQrPtduF5CJ3mK344eTZ53hspstXFJ-iEskA&s", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://blog.cdn.cmarix.com/blog/wp-content/uploads/2021/06/8-Killer-Kotlin-Tips-For-Android-App-Developers.png", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://www.aress.com/assets/images/inner-headers/android-app-header.webp", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiEjBilP-PBEbL7NAsVh5jU2PEYPgaGhh8-g&s", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4qoydCWxsFRz69Knzr-NX8B3rCaQYyEMtxg&s", ScaleTypes.CENTER_CROP))

        val imageSlider =binding.imageSlider
        imageSlider.setImageList(imageList)

    }


    companion object {

    }
}