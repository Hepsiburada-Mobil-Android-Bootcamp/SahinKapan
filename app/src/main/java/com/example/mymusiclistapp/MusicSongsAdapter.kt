package com.example.mymusiclistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MusicSongsAdapter(context: Context,val songImages:IntArray,val songNames: Array<String>,
                        val songSinger: Array<String>,val songTimes: Array<String>):
RecyclerView.Adapter<MusicSongsAdapter.MusicSongsViewHolder>(){

    private lateinit var mListener : onItemClickedListener

    interface onItemClickedListener{

        fun onItemClick(position : Int)


    }

    fun setOnItemClickListener(listener:onItemClickedListener){

        mListener=listener
    }


    class MusicSongsViewHolder(itemView: View, listener: onItemClickedListener):RecyclerView.ViewHolder(itemView) {

        val songsImages=itemView.findViewById<ImageView>(R.id.song_photo)
        val songsName=itemView.findViewById<TextView>(R.id.song_name)
        val songsTime=itemView.findViewById<TextView>(R.id.song_time)
        val songsSinger=itemView.findViewById<TextView>(R.id.song_singer)

        init {

            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)


            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicSongsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.songs_item, parent, false)
        return MusicSongsViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MusicSongsViewHolder, position: Int) {
        holder.songsImages.setImageResource(songImages[position])
        holder.songsName.text=songNames[position]
        holder.songsTime.text=songTimes[position]
        holder.songsSinger.text=songSinger[position]
    }

    override fun getItemCount(): Int {
        return songImages.size
    }


}