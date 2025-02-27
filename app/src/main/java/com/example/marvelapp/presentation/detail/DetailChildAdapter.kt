package com.example.marvelapp.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemChildDetailBinding
import com.example.marvelapp.framework.imageloader.ImageLoader

class DetailChildAdapter(
    private val detailChildList: List<DetailChildViewEntity>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailChildAdapter.DetailChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChildViewHolder {
        return DetailChildViewHolder.create(parent, imageLoader)
    }

    override fun onBindViewHolder(holder: DetailChildViewHolder, position: Int) {
        holder.bind(detailChildList[position])
    }

    override fun getItemCount(): Int = detailChildList.size

    class DetailChildViewHolder(
        itemBinding: ItemChildDetailBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val imageCategory: ImageView = itemBinding.imageItemCategory

        fun bind(detailChildList: DetailChildViewEntity) {
            imageLoader.loadImage(imageCategory, detailChildList.imageUrl)
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ): DetailChildViewHolder {
                val itemBinding = ItemChildDetailBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return DetailChildViewHolder(itemBinding, imageLoader)
            }
        }
    }
}