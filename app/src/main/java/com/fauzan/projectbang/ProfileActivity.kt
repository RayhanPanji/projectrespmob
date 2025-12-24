package com.fauzan.projectbang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fauzan.projectbang.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Profile Data (Simulasi)
        // Nanti bisa diambil dari Database/SharedPref
        binding.tvName.text = "Fauzan ProjectBang"

        // Load Gambar Profil
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=200") // Contoh URL
            .placeholder(R.mipmap.ic_launcher_round)
            .circleCrop()
            .into(binding.imgProfile)

        // 2. Setup Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 3. Setup Menu Clicks

        // Edit Profile
        binding.btnEditProfile.setOnClickListener {
            Toast.makeText(this, "Buka halaman Edit Username...", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, EditProfileActivity::class.java)
            // startActivity(intent)
        }

        // Ganti Password
        binding.btnChangePass.setOnClickListener {
            Toast.makeText(this, "Buka halaman Ganti Password...", Toast.LENGTH_SHORT).show()
        }

        // Alamat / Map
        binding.btnAddress.setOnClickListener {
            Toast.makeText(this, "Membuka Peta Alamat...", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, MapActivity::class.java)
            // startActivity(intent)
        }

        // Metode Pembayaran
        binding.btnPayment.setOnClickListener {
            Toast.makeText(this, "Atur Metode Pembayaran...", Toast.LENGTH_SHORT).show()
        }

        // Order History
        binding.btnOrderHistory.setOnClickListener {
            Toast.makeText(this, "Membuka Riwayat Pesanan...", Toast.LENGTH_SHORT).show()
        }

        // Logout
        binding.btnLogout.setOnClickListener {
            Toast.makeText(this, "Berhasil Keluar Akun", Toast.LENGTH_SHORT).show()
            // Logic logout (clear session) & kembali ke Login Page
        }
    }
}