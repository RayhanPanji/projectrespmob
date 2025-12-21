package com.fauzan.projectbang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzan.projectbang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inisialisasi ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategoryList()
        setupProductList()
    }

    private fun setupCategoryList() {
        // Data Dummy (Contoh)
        val categories = listOf(
            CategoryModel("Special", "https://images.unsplash.com/photo-1541167760496-1628856ab772?q=80&w=200&auto=format&fit=crop"),
            CategoryModel("Coffee", "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?q=80&w=200&auto=format&fit=crop"),
            CategoryModel("Milk Base", "https://images.unsplash.com/photo-1517701604599-bb29b5c7355c?q=80&w=200&auto=format&fit=crop"),
            CategoryModel("Tea", "https://images.unsplash.com/photo-1556679343-c7306c1976bc?q=80&w=200&auto=format&fit=crop"),
            CategoryModel("Pastry", "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?q=80&w=200&auto=format&fit=crop")
        )

        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryAdapter(categories)
            setHasFixedSize(true)
        }
    }

    private fun setupProductList() {
        // Data Dummy (Contoh)
        val products = listOf(
            ProductModel("Kopi Susu", "Rp. 21.000", "https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5?q=80&w=400"),
            ProductModel("Capucinno", "Rp. 21.000", "https://images.unsplash.com/photo-1572442388796-11668a67e53d?q=80&w=400"),
            ProductModel("Americano", "Rp. 21.000", "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?q=80&w=400"),
            ProductModel("Latte", "Rp. 24.000", "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?q=80&w=400"),
            ProductModel("Espresso", "Rp. 18.000", "https://images.unsplash.com/photo-1510591509098-f4fdc6d0ff04?q=80&w=400"),
            ProductModel("Mocha", "Rp. 25.000", "https://images.unsplash.com/photo-1578314675249-a6910f80cc4e?q=80&w=400")
        )

        binding.rvProducts.apply {
            // Gunakan Grid 2 Kolom
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductAdapter(products)
            isNestedScrollingEnabled = false // Agar scroll lancar di dalam NestedScrollView
        }
    }
}