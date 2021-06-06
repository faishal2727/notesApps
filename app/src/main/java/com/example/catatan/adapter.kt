package com.example.catatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(private val User : MutableList<ModelData> = mutableListOf(),private  val Litener : clickAdapter):RecyclerView.Adapter<adapter.userViewHolder>() {
    inner class userViewHolder(a : View) : RecyclerView.ViewHolder(a){
        val tvTitle : TextView = a.findViewById(R.id.tvTitle)
        val tvDesc : TextView = a.findViewById(R.id.tvDesc)

        fun bindView (tampung : ModelData){
            tvTitle.text = tampung.getTitle()
            tvDesc.text = tampung.getDesc()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.userViewHolder {
        return userViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false))
    }
    override fun getItemCount(): Int {
        return  User.size
    }
    override fun onBindViewHolder(holder: adapter.userViewHolder, position: Int) {
        holder.bindView(User[position])
        holder.itemView.setOnClickListener {
            Litener.onClick(User[position])
        }
    }
    fun setData(data : List<ModelData>){
        User.clear()
        User.addAll(data)
        notifyDataSetChanged()
    }
    interface clickAdapter {
        fun onClick(user : ModelData)
    }
}