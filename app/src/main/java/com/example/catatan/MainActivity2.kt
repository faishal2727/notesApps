package com.example.catatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        view()
        addData()
        btnBack()
    }
    private fun view(){
        realm = Realm.getDefaultInstance()
    }
    private fun addData(){
        btn_Save.setOnClickListener {
            realm.beginTransaction()
            try {
                val curend = realm.where(ModelData::class.java).max("Id")
                val nextid = if ( curend== null) 1 else curend.toInt()+1
                val data = realm.createObject(ModelData::class.java)
                data.setId(nextid)
                data.setTitle(etTitle.text.toString())
                data.setDesc(etDesc.text.toString())
                realm.commitTransaction()
                Toast.makeText(this,"Data Berhasil di Simpan",Toast.LENGTH_LONG).show()
            }
            catch (e:Exception){
                Toast.makeText(this,"Data Gagal di Simpan",Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    private fun btnBack(){
        btnBatal.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}