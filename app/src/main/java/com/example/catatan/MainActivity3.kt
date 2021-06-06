package com.example.catatan

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.activity_main3.view.*

class MainActivity3 : AppCompatActivity() {
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        title = "KotlinApp"
        view()
        getData()
        UpdateData()
        cuk()
        DeletDAta()
    }
    private fun view(){
        realm = Realm.getDefaultInstance()
    }
    private fun getData(){
        etTitle1.setText(intent.getStringExtra("Judul"))
        etDEsc1.setText(intent.getStringExtra("Isi"))
    }
    private fun UpdateData(){
        btnEdit.setOnClickListener {
            realm.beginTransaction()
            realm.where(ModelData::class.java).equalTo("Id",intent.getIntExtra("Id",1)).findFirst().let {
                it!!.setTitle(etTitle1.text.toString())
                it!!.setDesc(etDEsc1.text.toString())
            }
            realm.commitTransaction()
            Toast.makeText(this,"Catatan telah di Edit",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    private fun DeletDAta(){
        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Apakah Anda Yakin Ingin Menghapus Catatan ?")
                    .setPositiveButton("Iya") { dialog, which ->
            realm.beginTransaction()
            realm.where(ModelData::class.java).equalTo("Title", etTitle1.text.toString()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            Toast.makeText(this, "Catatan di Hapus", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
                    }
                    .setNegativeButton("Tidak"){dialog, which ->
                    }
                    .show()
        }
    }
    private fun cuk(){
        btnBack.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}