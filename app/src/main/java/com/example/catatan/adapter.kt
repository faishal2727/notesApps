package com.example.catatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catatan.databinding.ItemPostBinding

class adapter(private val User : MutableList<ModelData> = mutableListOf(),
              private val Litener : clickAdapter):RecyclerView.Adapter<adapter.userViewHolder>() {
    inner class userViewHolder(val binding: ItemPostBinding ) : RecyclerView.ViewHolder(binding.root){
        val tvTitle : TextView = binding.tvTitle
        fun bindView (tampung : ModelData){
            tvTitle.text = tampung.getTitle()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.userViewHolder {
        return userViewHolder (ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount(): Int {
        return  User.size
    }
    override fun onBindViewHolder(holder: adapter.userViewHolder, position: Int) {
        holder.bindView(User[position])
        holder.binding.apply {
        data.setOnClickListener {
        Litener.onClick(User[position])
        }
        }
            holder.binding.apply {
            edit.setOnClickListener {
                Litener.onEdit(User[position])
            }
        }
    }
    fun setData(data : List<ModelData>){
        User.clear()
        User.addAll(data)
        notifyDataSetChanged()
    }
    interface clickAdapter {
        fun onClick(user : ModelData)
        fun onEdit(user: ModelData)
    }
}