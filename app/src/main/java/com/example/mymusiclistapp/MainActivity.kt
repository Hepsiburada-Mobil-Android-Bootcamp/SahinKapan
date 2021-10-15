package com.example.mymusiclistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val loginButton by lazy { findViewById<View>(R.id.button_login) }
    val textUser by lazy { findViewById<EditText>(R.id.editText_username) }
    val textPassword by lazy {findViewById<EditText>(R.id.editText_password)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginButton.setOnClickListener {
            if(textUser.text.toString().equals("sahinkapan")
                && textPassword.text.toString().equals("12345")){
                val intent= Intent(this,MusicListActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Kullanıcı adı veya şifre yanlış!",Toast.LENGTH_LONG).show()
            }
        }



    }
}