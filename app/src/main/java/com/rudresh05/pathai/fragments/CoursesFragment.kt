package com.rudresh05.pathai.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.rudresh05.pathai.R
import com.rudresh05.pathai.adapters.CategoryAdapter
import com.rudresh05.pathai.adapters.CourseAdapter
import com.rudresh05.pathai.databinding.FragmentCoursesBinding
import com.rudresh05.pathai.databinding.FragmentHomeBinding
import com.rudresh05.pathai.models.dataModels.Course

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var clist = ArrayList<Course>()
        clist.add(Course(title = "HTML", image = "https://kinsta.com/wp-content/uploads/2021/03/HTML-5-Badge-Logo.png"))
        clist.add(Course(title = "CSS", image = "https://colorlib.com/wp/wp-content/uploads/sites/2/creative-css3-tutorials.jpg"))
        clist.add(Course(title = "JAVASCRIPT"))
        clist.add(Course(title = "JAVA"))
        clist.add(Course(title = "REACT"))

        var recyclerView = binding.recentActivityRecycler
        var adapter = CourseAdapter(requireContext(),clist)
        recyclerView.adapter = adapter

        var cateRecycler = binding.categoryRecyclerView
        var catAdapter = CategoryAdapter(requireContext(),clist)
        cateRecycler.adapter = catAdapter

        binding.categoryRecyclerView.setOnClickListener {

            Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
        }
    }





    companion object {

    }
}