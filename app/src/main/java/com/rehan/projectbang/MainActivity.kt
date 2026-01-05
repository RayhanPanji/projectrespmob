package com.rehan.projectbang

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rehan.projectbang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var coffeeAdapter: ProductAdapter
    private lateinit var milkAdapter: ProductAdapter
    private lateinit var pastryAdapter: PastryAdapter

    private var listCoffeeOriginal = listOf<ProductModel>()
    private var listMilkOriginal = listOf<ProductModel>()
    private var listPastryOriginal = listOf<ProductModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Memuat gambar profil dari drawable
        Glide.with(this)
            .load(R.drawable.bjorlil)
            .circleCrop()
            .into(binding.imgProfile)

        setupData()
        setupCategoryList()
        setupLists()
        setupSearch()

        binding.imgProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.navHome.setOnClickListener { }

        binding.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }

    private fun setupData() {
        listCoffeeOriginal = listOf(
            ProductModel(title = "Kopi Susu", price = "Rp. 21.000", imageUrl = "https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5?q=80&w=400"),
            ProductModel(title = "Cappuccino", price = "Rp. 21.000", imageUrl = "https://images.unsplash.com/photo-1572442388796-11668a67e53d?q=80&w=400"),
            ProductModel(title = "Americano", price = "Rp. 21.000", imageUrl = "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?q=80&w=400"),
            ProductModel(title = "Latte", price = "Rp. 24.000", imageUrl = "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?q=80&w=400")
        )

        listMilkOriginal = listOf(
            ProductModel(title = "Biscoff", price = "Rp. 25.000", imageUrl = "https://images.unsplash.com/photo-1588785392688-3358a9785b88?q=80&w=600"),
            ProductModel(title = "Berry Land", price = "Rp. 23.000", imageUrl = "https://images.unsplash.com/photo-1502741224143-943393a86834?q=80&w=600"),
            ProductModel(title = "Matcha", price = "Rp. 24.000", imageUrl = "https://images.unsplash.com/photo-1515823064-d6e0c04616a7?q=80&w=400")
        )

        listPastryOriginal = listOf(
            ProductModel(title = "Butter Croissant", price = "Rp. 40.000", imageUrl = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?q=80&w=600"),
            ProductModel(title = "Almond Croissant", price = "Rp. 45.000", imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?q=80&w=600"),
            ProductModel(title = "Kouign Amann", price = "Rp. 40.000", imageUrl = "https://images.unsplash.com/photo-1558326567-98ae2405596b?q=80&w=600")
        )
    }

    private fun setupCategoryList() {
        val categories = listOf(
            CategoryModel(title = "Special", imageUrl = "https://images.unsplash.com/photo-1541167760496-1628856ab772?q=80&w=200&auto=format&fit=crop"),
            CategoryModel(title = "Coffee", imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?q=80&w=200&auto=format&fit=crop"),
            CategoryModel(title = "Milk Base", imageUrl = "https://images.unsplash.com/photo-1579954115545-a95591f28bfc?q=80&w=200&auto=format&fit=crop"),
            CategoryModel(title = "Tea", imageUrl = "https://images.unsplash.com/photo-1556679343-c7306c1976bc?q=80&w=200&auto=format&fit=crop"),
            CategoryModel(title = "Pastry", imageUrl = "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?q=80&w=200&auto=format&fit=crop")
        )
        binding.rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = CategoryAdapter(categories)
    }

    private fun setupLists() {
        coffeeAdapter = ProductAdapter(listCoffeeOriginal)
        milkAdapter = ProductAdapter(listMilkOriginal)
        pastryAdapter = PastryAdapter(listPastryOriginal)

        binding.rvCoffee.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = coffeeAdapter
        }

        binding.rvMilkBase.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = milkAdapter
        }

        binding.rvPastry.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = pastryAdapter
        }
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterMenu(s.toString().trim())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterMenu(query: String) {
        coffeeAdapter.updateData(listCoffeeOriginal.filter { it.title.contains(query, ignoreCase = true) })
        milkAdapter.updateData(listMilkOriginal.filter { it.title.contains(query, ignoreCase = true) })
        pastryAdapter.updateData(listPastryOriginal.filter { it.title.contains(query, ignoreCase = true) })
    }
}