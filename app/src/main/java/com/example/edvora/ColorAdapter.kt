package com.example.edvora

import android.graphics.Color
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.ui.DrawingLine

class ColorAdapter(): RecyclerView.Adapter<ColorAdapter.Holder>() {
     var color_selected=Color.BLACK
    private var data= arrayOf(3,4)

    class Holder(view:View) :RecyclerView.ViewHolder(view){
        val consLayout: ConstraintLayout
        init {
            consLayout=view.findViewById(R.id.color_container)
        }
    }
    fun getColor(): Int {
        return color_selected
        notifyDataSetChanged()
    }
    fun setcolorData(data: Array<Int>){
        this.data=data;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.color_items,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.consLayout.setBackgroundColor(data[position])
        holder.consLayout.setOnClickListener {
            color_selected=data[position]
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}