package com.example.mymusiclistapp.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mymusiclistapp.ConnectionLiveData
import com.example.mymusiclistapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var buttonPlay:ImageButton



/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var runnable: Runnable
    private var handler = Handler()





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
        val viewFavor:View=inflater.inflate(R.layout.fragment_favourite, container, false)

        val mediaPlayer:MediaPlayer = MediaPlayer.create(this.context,R.raw.music)

        val seekBar:SeekBar=viewFavor.findViewById(R.id.seekbar)






        buttonPlay=viewFavor.findViewById(R.id.play_btn)

        buttonPlay.setOnClickListener {

            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()

                buttonPlay.setImageResource(R.drawable.pause_icon)
            }else{
                mediaPlayer.pause()
                buttonPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24 )
            }

        }

        //Barı çekince şarkı değişiyor
        seekBar.progress=0
        seekBar.max=mediaPlayer.duration
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if (changed){
                    mediaPlayer.seekTo(progress)
                }


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }


        })
        //Progress barın ilerlemesi için
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)

        mediaPlayer.setOnCompletionListener {
            buttonPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekBar.progress = 0
        }







        return viewFavor
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}