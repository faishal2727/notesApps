package com.example.catatan

import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.item_post.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter : adapter
    lateinit var realm: Realm
    private var back : Long = 0
    private var toast : Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllDAta()
        ParsingAdd()
        gitHub()
    }
    private fun gitHub(){
        btnGithub.setOnClickListener {
            val github = Intent(Intent.ACTION_VIEW)
            github.setData(Uri.parse("https://www.github.com/muhfaishalrizal/notesApps"))
            startActivity(github)
        }
    }

    override fun onResume() {
        super.onResume()
        nama()
    }
    private fun nama(){
        val nama1 = intent.getStringExtra("satu")
        val full = "$nama1"
        NamaUser.text = "$full"
    }
    override fun onBackPressed() {
        if(back + 2000 > System.currentTimeMillis()) {
            toast?.cancel()
            super.onBackPressed()
            return
        }
        back = System.currentTimeMillis()
        finish()
    }
    private fun getAllDAta(){
        binding.RV.layoutManager = LinearLayoutManager(this)
        adapter = adapter(mutableListOf(),object : adapter.clickAdapter{
            override fun onClick(user: ModelData) {
                startActivity(Intent(this@MainActivity,detailActivity::class.java)
                    .putExtra("Id",user.getId())
                    .putExtra("Judul",user.getTitle())
                    .putExtra("Isi",user.getDesc())
                )
                finish()
            }
            override fun onEdit(user: ModelData) {
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