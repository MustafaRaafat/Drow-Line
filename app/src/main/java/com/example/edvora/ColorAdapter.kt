package com.example.edvora

import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(private val data:Array<Int>): RecyclerView.Adapter<ColorAdapter.Holder>() {


    class Holder(view:View) :RecyclerView.ViewHolder(view){
        val ConsLayout: ConstraintLayout
        init {
            ConsLayout=view.findViewById(R.id.color_container)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_items,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.ConsLayout.setBackgroundResource(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}