package com.rudresh05.pathai.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rudresh05.pathai.databinding.ListItemVideoBinding
import com.rudresh05.pathai.models.VideoModel

class VideoAdapter(private val videoModels: List<VideoModel>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    //create view holder for recycler view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val binding = ListItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    //bind data to view holder
    override fun onBindViewHolder(
        holder: VideoViewHolder,
        position: Int
    ) {
        holder.bind(videoModels[position])
        holder.itemView.setOnClickListener {
            onItemClick(videoModels[position].id)
        }

    }

    // return size of list
    override fun getItemCount(): Int = videoModels.size

    class VideoViewHolder(private val binding: ListItemVideoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(videoModel: VideoModel){ // function for data binding

            binding.textViewTitle.text = videoModel.title
            binding.textViewChannel.text = videoModel.channelName

            // Use Glide to load the image from the URL into the ImageView
            Glide.with(itemView.context)
                .load(videoModel.thumbnailUrl) // The URL of the image
                .into(binding.imageViewThumbnail) // The ImageView to load it into


        }
     }
}