package com.example.mymusiclistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mymusiclistapp.fragments.FavouriteFragment
import com.example.mymusiclistapp.fragments.MessageFragment
import com.example.mymusiclistapp.fragments.MusicListFragment
import com.example.mymusiclistapp.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MusicListActivity : AppCompatActivity() {

    private val musicListsFragment = MusicListFragment()
    private val favouriteFragment = FavouriteFragment()
    private val searchFragment = SearchFragment()
    private val messageFragment = MessageFragment()

    private lateinit var cld:ConnectionLiveData
    private lateinit var wifiImage: ImageView
    private lateinit var textConnection: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_list)
        replaceFragment(musicListsFragment)

        val bottomNavigation: ChipNavigationBar=findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener {
            when(it){
                R.id.ic_musics->replaceFragment(musicListsFragment)
                R.id.ic_favourite->replaceFragment(favouriteFragment)
                R.id.ic_search->replaceFragment(searchFragment)
                R.id.ic_message->replaceFragment(messageFragment)
            }
            true
        }

        /*when(it.itemId){
            R.id.ic_musics->replaceFragment(musicListsFragment)
            R.id.ic_favourite->replaceFragment(favouriteFragment)
            R.id.ic_search->replaceFragment(searchFragment)
            R.id.ic_message->replaceFragment(messageFragment)
        }
        true*/

        wifiImage=findViewById(R.id.wifi_icon)
        textConnection=findViewById(R.id.connectionText)

        checNetworkConnection()

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }

    }


    private fun checNetworkConnection() {
        cld = ConnectionLiveData(application)

        cld.observe(this,{ isConnected ->
            if(isConnected){
                wifiImage.setImageResource(R.drawable.wifi_icon)
                textConnection.setText(R.string.connection)

            }else{
                wifiImage.setImageResource(R.drawable.wifi_off_icon)
                textConnection.setText(R.string.notconnection)

            }
        })
    }


}