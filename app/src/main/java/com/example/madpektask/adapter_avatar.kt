package com.example.madpektask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madpektask.model.Data
import com.squareup.picasso.Picasso

class adapter_avatar(private val data: ArrayList<Data>): RecyclerView.Adapter<adapter_avatar.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter_avatar.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: adapter_avatar.MyViewHolder, position: Int) {
        val currentItem = data[position]
        Picasso.get().load(currentItem.avatar).into(holder.image)
        holder.emailid.text = currentItem.email.toString()
        holder.firstname.text = currentItem.first_name.toString()
        holder.lastname.text = currentItem.last_name.toString()
        holder.personid.text = currentItem.id.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val emailid: TextView = itemView.findViewById(R.id.emailid)
        val image: ImageView = itemView.findViewById(R.id.avatarimg)
        val firstname: TextView = itemView.findViewById(R.id.firstname)
        val lastname: TextView = itemView.findViewById(R.id.lastname)
        val personid: TextView = itemView.findViewById(R.id.personid)
    }
}