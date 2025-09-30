package com.rudresh05.pathai.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.addCallback
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.rudresh05.pathai.R
import com.rudresh05.pathai.adapters.CategoryAdapter
import com.rudresh05.pathai.adapters.CourseAdapter
import com.rudresh05.pathai.databinding.FragmentCoursesBinding
import com.rudresh05.pathai.databinding.FragmentHomeBinding
import com.rudresh05.pathai.models.dataModels.Course
import androidx.core.view.isVisible

class CoursesFragment : Fragment() {
    private lateinit var binding: FragmentCoursesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment\
        binding = FragmentCoursesBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var clist = ArrayList<Course>()
        clist.add(Course(
            id = "1",
            title = "ANDROID",
            image = "https://corizo.in/wp-content/uploads/2024/11/Android-logo.jpg",
            content = "https://developer.android.com/get-started/overview",
        ))
        clist.add(Course(id = "2",
            title = "DSA",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2tmKCyWGcC5wCCSGkrk6xYd0tiqysvrIsoQ&s",
            content="https://geeksforgeeks.org/dsa/dsa-tutorial-learn-data-structures-and-algorithms/"))
        clist.add(Course(id = "3",
            title = "HTML",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDxlqmTPx1zm4lpBc4gSKACq8L85CotcVPnA&s",
            content = "https://www.geeksforgeeks.org/html/html-tutorial/"))
        clist.add(Course(id = "4",
            title = "CSS",
            image = "https://colorlib.com/wp/wp-content/uploads/sites/2/creative-css3-tutorials.jpg",
            content = "https://www.geeksforgeeks.org/css/css-tutorial/"))
        clist.add(Course(id = "5",
            title = "JAVASCRIPT",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyVIqAglaNUpkS6q5817KhYfBs72Kyejui7w&s",
            content = "https://www.geeksforgeeks.org/javascript/javascript-tutorial/"))
        clist.add(Course(id = "6",
            title = "JAVA",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXeNvpYl62B2Iohxp-EO4AFQgE2uRJmNebSw&s",
            content = "https://docs.oracle.com/javase/tutorial/java/index.html"))
        clist.add(Course(id = "7",
            title = "REACT",
            image = "https://i.pinimg.com/736x/5c/7b/53/5c7b53a7be1dd267f24f7559584d1345.jpg",
            content = "https://react.dev/learn"))

        clist.add(Course(id = "8",
            title = "CYBER",
            image = "https://assets.weforum.org/article/image/5Mvrz-vmTr62JXqvm4BDHJOM2tHyErG6f6KFSvYb2vM.jpg",
            content = "https://www.geeksforgeeks.org/ethical-hacking/cyber-security-tutorial/"))

        var recyclerView = binding.recentActivityRecycler
        var adapter = CourseAdapter(requireContext(),clist){course ->
        //    Toast.makeText(requireContext(),"Course Clicked ${course.title}", Toast.LENGTH_LONG).show()
            openCourseInWebView(course.content)
        }
        recyclerView.adapter = adapter

        var cateRecycler = binding.categoryRecyclerView
        var catAdapter = CategoryAdapter(requireContext(),clist){course ->
           // Toast.makeText(requireContext(), "item click ${course.title}", Toast.LENGTH_SHORT).show()
            openCourseInWebView(course.content)

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(binding.courseWebView.isVisible){
                binding.courseWebView.visibility = View.GONE
                binding.categoryRecyclerView.visibility = View.VISIBLE
                binding.recentActivityRecycler.visibility = View.VISIBLE
                binding.linear1.visibility = View.VISIBLE
                binding.linear2.visibility = View.VISIBLE
                binding.cardHeader.visibility = View.VISIBLE
                binding.editText.visibility = View.VISIBLE
                binding.ivLogo.visibility = View.VISIBLE
            }
        }
        cateRecycler.adapter = catAdapter

        binding.categoryRecyclerView.setOnClickListener {

            Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()


        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun openCourseInWebView(url: String?) {
        binding.courseWebView.settings.apply {
            javaScriptEnabled = true
            builtInZoomControls = true
            displayZoomControls = false
            useWideViewPort = true
            loadWithOverviewMode = true
        }
        binding.courseWebView.webViewClient = WebViewClient()

        binding.courseWebView.visibility = View.VISIBLE
        binding.categoryRecyclerView.visibility = View.GONE
        binding.recentActivityRecycler.visibility = View.GONE
        binding.linear1.visibility = View.GONE
        binding.linear2.visibility = View.GONE
        binding.cardHeader.visibility = View.GONE
        binding.editText.visibility = View.GONE
        binding.ivLogo.visibility = View.GONE

        binding.courseWebView.loadUrl(url ?: "https://www.geeksforgeeks.org/")
    }






    companion object {

    }
}