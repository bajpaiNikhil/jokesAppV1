package com.example.jokesappv1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter (val jokeCatList : List<String> , val listener : (String) -> Unit): RecyclerView.Adapter<JokeAdapter.JokeHolder>(){
    inner class JokeHolder(view : View) : RecyclerView.ViewHolder(view) {
        val catIs = view.findViewById<TextView>(R.id.itemCatTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category , parent , false)
        return JokeHolder(view)
    }

    override fun onBindViewHolder(holder: JokeHolder, position: Int) {
        val currentItem = jokeCatList[position]
        holder.catIs.text = currentItem

        holder.itemView.setOnClickListener {
            listener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return jokeCatList.size
    }

}