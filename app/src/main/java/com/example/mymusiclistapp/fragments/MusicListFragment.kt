package com.example.mymusiclistapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymusiclistapp.MusicListActivity
import com.example.mymusiclistapp.MusicSignsAdapter
import com.example.mymusiclistapp.MusicSongsAdapter
import com.example.mymusiclistapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MusicListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MusicListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rcSigns:RecyclerView

    private lateinit var rcSongs:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val viewThis:View =inflater.inflate(R.layout.fragment_music_list, container, false)

        rcSigns=viewThis.findViewById<RecyclerView>(R.id.recycler_view_types)
        val layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        rcSigns.layoutManager=layoutManager
        rcSigns.setHasFixedSize(true)


        val signImages:IntArray= intArrayOf(R.drawable.songs_icon,R.drawable.album_icon,R.drawable.artists_icon,
            R.drawable.concerts_icon,R.drawable.settings_icon,R.drawable.mute_icon)

        val nameSigns= arrayOf<String>("Songs","Albums","Artists","Concerts","Settings","Mute")

        //rcSigns.layoutManager=layoutManager
        //rcSigns.setHasFixedSize(true)

        rcSigns.adapter= this.context?.let { MusicSignsAdapter(it,signImages,nameSigns ) }



        rcSongs=viewThis.findViewById(R.id.recycler_songs)
        val layoutManagerSongs =LinearLayoutManager(this.context,LinearLayoutManager.VERTICAL,false)
        rcSongs.layoutManager=layoutManagerSongs
        rcSongs.setHasFixedSize(true)


        val songsImages:IntArray= intArrayOf(R.drawable.s_1_icon,R.drawable.s_2_icon,R.drawable.s_3_icon,
            R.drawable.s_4_icon,R.drawable.s_5_icon,R.drawable.s_6_icon)

        val songsNames = arrayOf<String>("You Already Know","Till I Collapse","Eatin","In The Pit","Scary Moves","Paparazzi")

        val songsSingers = arrayOf<String>("Lloyd Banks","Eminem","Chamillionaire","Lil Jon","Bad Meets Evil","Xzibit")

        val songsTimes = arrayOf<String>("4 min","5 min","3 min","6 min","5 min","4 min")

        var adapter =  this.context?.let { MusicSongsAdapter(it,songsImages,songsNames,songsSingers,songsTimes ) }

        rcSongs.adapter = adapter
        adapter?.setOnItemClickListener(object : MusicSongsAdapter.onItemClickedListener {
            override fun onItemClick(position: Int) {

                Toast.makeText(activity,songsNames[position],Toast.LENGTH_SHORT)
                val favouriteFragment = FavouriteFragment()
                val fragmentManager =activity?.supportFragmentManager
                val fragmentTransaction:FragmentTransaction=fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.fragment_container,favouriteFragment)
                fragmentTransaction.commit()

            }

        })


        return viewThis
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MusicListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MusicListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}