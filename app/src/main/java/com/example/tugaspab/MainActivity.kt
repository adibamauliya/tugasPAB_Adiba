package com.example.tugaspab

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.content.res.ColorStateList
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Switch
import android.widget.Toast

class MainActivity : Activity() {

    private val hijau = Color.rgb(49, 92, 67)
    private val abu = Color.rgb(232, 237, 231)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root = findViewById<LinearLayout>(R.id.rootLayout)
        val header = findViewById<LinearLayout>(R.id.headerLayout)

        val home = findViewById<LinearLayout>(R.id.homePage)
        val profile = findViewById<LinearLayout>(R.id.profilePage)
        val settings = findViewById<LinearLayout>(R.id.settingsPage)

        val headerTitle = findViewById<TextView>(R.id.headerTitle)
        val headerSubtitle = findViewById<TextView>(R.id.headerSubtitle)

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnProfil = findViewById<Button>(R.id.btnProfil)
        val btnPengaturan = findViewById<Button>(R.id.btnPengaturan)
        val btnMulai = findViewById<Button>(R.id.btnMulai)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val inputNama = findViewById<EditText>(R.id.inputNama)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val hasilProfil = findViewById<TextView>(R.id.hasilProfil)

        val switchNotifikasi =
            findViewById<Switch>(R.id.switchNotifikasi)
        val switchTema = findViewById<Switch>(R.id.switchTema)
        val hasilPengaturan =
            findViewById<TextView>(R.id.hasilPengaturan)

        val tombol = listOf(btnHome, btnProfil, btnPengaturan)

        fun bukaHalaman(nama: String) {
            home.visibility = View.GONE
            profile.visibility = View.GONE
            settings.visibility = View.GONE

            when (nama) {
                "Home" -> {
                    home.visibility = View.VISIBLE
                    headerTitle.text = "Halo, Pengguna!"
                    headerSubtitle.text = "Selamat datang di aplikasi kamu."
                }

                "Profil" -> {
                    profile.visibility = View.VISIBLE
                    headerTitle.text = "Profil Saya"
                    headerSubtitle.text = "Kelola informasi pribadi kamu."
                }

                "Setelan" -> {
                    settings.visibility = View.VISIBLE
                    headerTitle.text = "Pengaturan"
                    headerSubtitle.text = "Atur aplikasi sesuai kebutuhanmu."
                }
            }

            tombol.forEach { button ->
                val aktif = when (nama) {
                    "Home" -> button == btnHome
                    "Profil" -> button == btnProfil
                    else -> button == btnPengaturan
                }

                button.backgroundTintList = ColorStateList.valueOf(
                    if (aktif) hijau else abu
                )

                button.setTextColor(
                    if (aktif) Color.WHITE else hijau
                )
            }
        }

        btnHome.setOnClickListener {
            bukaHalaman("Home")
        }

        btnProfil.setOnClickListener {
            bukaHalaman("Profil")
        }

        btnPengaturan.setOnClickListener {
            bukaHalaman("Setelan")
        }

        btnMulai.setOnClickListener {
            bukaHalaman("Profil")
        }

        // Simpan dan tampilkan informasi profil
        btnSimpan.setOnClickListener {
            val nama = inputNama.text.toString().trim()
            val email = inputEmail.text.toString().trim()

            if (nama.isEmpty() || email.isEmpty()) {
                Toast.makeText(
                    this,
                    "Nama dan email harus diisi!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email).matches()) {
                inputEmail.error = "Format email belum benar"
            } else {
                hasilProfil.text =
                    "Profil berhasil disimpan!\n\n" +
                            "Nama: $nama\nEmail: $email"

                Toast.makeText(
                    this,
                    "Profil berhasil disimpan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Pengaturan notifikasi
        switchNotifikasi.setOnCheckedChangeListener { _, aktif ->
            hasilPengaturan.text =
                if (aktif) {
                    "Notifikasi aktif"
                } else {
                    "Notifikasi nonaktif"
                }
        }

        // Pengaturan tema
        switchTema.setOnCheckedChangeListener { _, gelap ->
            val warnaLatar = if (gelap) {
                Color.rgb(35, 40, 36)
            } else {
                Color.rgb(244, 247, 242)
            }

            val warnaKartu = if (gelap) {
                Color.rgb(55, 61, 56)
            } else {
                Color.WHITE
            }

            val warnaTeks = if (gelap) {
                Color.WHITE
            } else {
                Color.rgb(49, 92, 67)
            }

            root.setBackgroundColor(warnaLatar)
            home.setBackgroundColor(warnaKartu)
            profile.setBackgroundColor(warnaKartu)
            settings.setBackgroundColor(warnaKartu)

            hasilProfil.setTextColor(warnaTeks)
            hasilPengaturan.setTextColor(warnaTeks)

            Toast.makeText(
                this,
                if (gelap) "Mode gelap aktif" else "Mode terang aktif",
                Toast.LENGTH_SHORT
            ).show()
        }

        bukaHalaman("Home")
    }
}
