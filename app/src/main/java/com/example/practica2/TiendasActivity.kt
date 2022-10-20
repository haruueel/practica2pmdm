package com.example.practica2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TiendasActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiendas)
    }

    override fun onStart() {
        mediaPlayer = MediaPlayer.create(this, R.raw.ai_2)
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.seekTo(ScrollingActivity.g)
        mediaPlayer?.start()
    }
}