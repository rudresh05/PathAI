package com.rudresh05.pathai.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rudresh05.pathai.R
import com.rudresh05.pathai.adapters.VideoAdapter.VideoViewHolder
import com.rudresh05.pathai.databinding.ItemCourseBinding
import com.rudresh05.pathai.databinding.ListItemVideoBinding
import com.rudresh05.pathai.models.dataModels.Course

class CourseAdapter(var context : Context, var cList: ArrayList<Course>): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {
        holder.cTittle.text =  cList[position].title;
        Glide.with(context).load(cList[position].image).placeholder(R.drawable.person).into(holder.cImg)
    }

    override fun getItemCount(): Int {
       return cList.size
    }

    class CourseViewHolder(private val binding: ItemCourseBinding): RecyclerView.ViewHolder(binding.root){
        var cImg = binding.ivCourseImg
        var cTittle = binding.tvCourseTittle
    }
}