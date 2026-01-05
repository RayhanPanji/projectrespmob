package com.rehan.projectbang

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rehan.projectbang.databinding.ItemProductBinding

class ProductAdapter(private var items: List<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvProductName.text = item.title
        holder.binding.tvProductPrice.text = item.price

        Glide.with(holder.itemView.context)
            .load(item.imageUrl) // KEMBALI MENGGUNAKAN URL
            .centerCrop()
            .into(holder.binding.imgProduct)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("EXTRA_TITLE", item.title)
                putExtra("EXTRA_PRICE", item.price)
                putExtra("EXTRA_IMAGE", item.imageUrl) // KEMBALI MENGGUNAKAN URL
                putExtra("EXTRA_CATEGORY", "Drink")
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<ProductModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}