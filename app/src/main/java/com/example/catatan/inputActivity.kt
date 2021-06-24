package com.example.catatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_input.*

class inputActivity : AppCompatActivity() {
    private var back : Long = 0
    private var toast : Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        logIn()
    }
    override fun onResume() {
        super.onResume()
        logIn()
    }
    private fun logIn() {
        btnMasuk.setOnClickListener {
            if (etInputNama.text.toString().isNotEmpty()) {
                Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show()
                val pertama = etInputNama.text.toString()
                Intent(Intent(this, MainActivity::class.java)).also {
                    it.putExtra("satu", pertama)
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, "Silahkan Inputkan Nama Anda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        if (back + 2000 > System.currentTimeMillis()) {
            toast?.cancel()
            super.onBackPressed()
            return
        } else {
            toast = Toast.makeText(
                applicationContext,
                "Tekan Satu Kali Lagi Untuk Keluar",
                Toast.LENGTH_LONG
            )
            toast?.show()
        }
        back = System.currentTimeMillis()
    }
}