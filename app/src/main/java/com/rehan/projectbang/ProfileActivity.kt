package com.rehan.projectbang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rehan.projectbang.databinding.ActivityProfileBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ProfileActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProfileData()
        setupMenuClicks()
        setupBottomNav()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val lokasiSaya = LatLng(-7.8332349, 110.3809325)
        mMap.addMarker(MarkerOptions().position(lokasiSaya).title("Lokasi Saya"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasiSaya, 15.0f))
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun setupProfileData() {
        binding.tvName.text = "MUHAMMAD RAYHAN PANJI"

        Glide.with(this)
            .load(R.drawable.bjorlil)
            .placeholder(R.mipmap.ic_launcher_round)
            .circleCrop()
            .into(binding.imgProfile)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupMenuClicks() {
        binding.btnEditProfile.setOnClickListener {
            Toast.makeText(this, "Buka halaman Edit Profil...", Toast.LENGTH_SHORT).show()
        }

        binding.btnChangePass.setOnClickListener {
            Toast.makeText(this, "Buka halaman Ganti Password...", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddress.setOnClickListener {
            Toast.makeText(this, "Membuka Peta Alamat...", Toast.LENGTH_SHORT).show()
        }

        binding.btnPayment.setOnClickListener {
            Toast.makeText(this, "Atur Metode Pembayaran...", Toast.LENGTH_SHORT).show()
        }

        // --- BAGIAN PENTING: ARAHKAN KE HISTORY ACTIVITY ---
        binding.btnOrderHistory.setOnClickListener {
            // Pastikan ini HistoryActivity::class.java
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            Toast.makeText(this, "Berhasil Keluar Akun", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNav() {
        // Klik HOME -> Pindah ke MainActivity
        binding.navHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            overridePendingTransition(0, 0)
            finish()
        }

        // Klik CART -> Pindah ke CartActivity
        binding.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }

        // Klik PROFILE -> Diam saja (karena sedang di sini)
    }
}
