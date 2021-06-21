package com.example.catatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catatan.databinding.ActivityDetailBinding
import io.realm.Realm
import io.realm.mongodb.User
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.Collections.addAll

class detailActivity : AppCompatActivity() {
    lateinit var realm : Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getData()
        view()
        btnBack()
    }
    private fun view(){
        realm = Realm.getDefaultInstance()
    }
    private fun getData(){
        textJudul.setText(intent.getStringExtra("Judul"))
        textIsi.setText(intent.getStringExtra("Isi"))
    }
    private fun btnBack(){
        btnKembali.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}


