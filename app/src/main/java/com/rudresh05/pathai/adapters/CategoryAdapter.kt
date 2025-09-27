package com.rudresh05.pathai.adapters

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rudresh05.pathai.databinding.ItemCategoryBinding
import com.rudresh05.pathai.models.dataModels.Course

class CategoryAdapter(var context : Context, var cList: ArrayList<Course>, private val onItemClick:(Course) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
       holder.binding.ivCateCourseTitle.text = cList[position].title
        Glide.with(context).load(cList[position].image).placeholder(com.rudresh05.pathai.R.drawable.person).into(holder.cImg)
holder.itemView.setOnClickListener {
    onItemClick(cList[position])
}

    }

    override fun getItemCount(): Int {
        return cList.size
    }


    class CategoryViewHolder(var binding : ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        var cImg = binding.ivCateCourseImg
        var cTittle = binding.ivCateCourseTitle
    }
}
