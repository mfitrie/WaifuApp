package com.example.waifuapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash_screen.*

@SuppressLint("CustomSplashScreen")
class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        Handler().postDelayed({
//                              val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        },2000)

        iv_anime.alpha = 0f
        iv_anime.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this@splashScreen, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}