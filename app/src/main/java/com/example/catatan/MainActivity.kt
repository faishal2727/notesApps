package com.example.catatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catatan.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.Realm.*
import io.realm.kotlin.toChangesetFlow
import io.realm.mongodb.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter : adapter
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllDAta()
        ParsingAdd()
    }
    private fun getAllDAta(){
        binding.RV.layoutManager = LinearLayoutManager(this)
        adapter = adapter(mutableListOf(),object : adapter.clickAdapter{
            override fun onClick(user: ModelData) {
                startActivity(Intent(this@MainActivity,MainActivity3::class.java)
                    .putExtra("Id",user.getId())
                    .putExtra("Judul",user.getTitle())
                    .putExtra("Isi",user.getDesc())
                )
                finish()
            }
        })
        binding.RV.adapter = adapter
        realm = getDefaultInstance()
        realm.where(ModelData::class.java).findAll().let {
            adapter.setData(it)
        }
    }
    private fun ParsingAdd(){
        btnAdd.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }
    }
}