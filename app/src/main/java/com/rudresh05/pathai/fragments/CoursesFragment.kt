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
        clist.add(Course(id = "1",title = "ANDROID", image = "https://corizo.in/wp-content/uploads/2024/11/Android-logo.jpg"))
        clist.add(Course(id = "2",title = "HTML", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDxlqmTPx1zm4lpBc4gSKACq8L85CotcVPnA&s"))
        clist.add(Course(id = "3",title = "CSS", image = "https://colorlib.com/wp/wp-content/uploads/sites/2/creative-css3-tutorials.jpg"))
        clist.add(Course(id = "4",title = "JAVASCRIPT", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyVIqAglaNUpkS6q5817KhYfBs72Kyejui7w&s"))
        clist.add(Course(id = "5",title = "JAVA", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXeNvpYl62B2Iohxp-EO4AFQgE2uRJmNebSw&s"))
        clist.add(Course(id = "6",title = "REACT", image = "https://i.pinimg.com/736x/5c/7b/53/5c7b53a7be1dd267f24f7559584d1345.jpg"))

        var recyclerView = binding.recentActivityRecycler
        var adapter = CourseAdapter(requireContext(),clist){course ->
            Toast.makeText(requireContext(),"Course Clicked ${course.title}", Toast.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter

        var cateRecycler = binding.categoryRecyclerView
        var catAdapter = CategoryAdapter(requireContext(),clist){course ->
            Toast.makeText(requireContext(), "item click ${course.title}", Toast.LENGTH_SHORT).show()
        }
        cateRecycler.adapter = catAdapter

        binding.categoryRecyclerView.setOnClickListener {

            Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
        }
    }





    companion object {

    }
}