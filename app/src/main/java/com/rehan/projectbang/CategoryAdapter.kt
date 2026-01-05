package com.rehan.projectbang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rehan.projectbang.databinding.ItemCategoryBinding

class CategoryAdapter(private val items: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvCategoryName.text = item.title

        // Load gambar bulat dari URL
        Glide.with(holder.itemView.context)
            .load(item.imageUrl) // KEMBALI MENGGUNAKAN URL
            .circleCrop()
            .into(holder.binding.imgCategory)
    }

    override fun getItemCount() = items.size
}