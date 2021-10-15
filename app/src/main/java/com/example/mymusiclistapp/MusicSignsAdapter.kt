package com.example.mymusiclistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicSignsAdapter(context: Context,val signImages:IntArray,val nameSigns: Array<String>):
    RecyclerView.Adapter<MusicSignsAdapter.MusicSignsViewHolder>() {

        class MusicSignsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val signs=itemView.findViewById<ImageView>(R.id.sign_view)
            val signsText=itemView.findViewById<TextView>(R.id.signs_text)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicSignsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return MusicSignsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicSignsViewHolder, position: Int) {
        holder.signsText.text=nameSigns[position]
        holder.signs.setImageResource(signImages[position])
    }

    override fun getItemCount(): Int {
        return signImages.size
    }


}