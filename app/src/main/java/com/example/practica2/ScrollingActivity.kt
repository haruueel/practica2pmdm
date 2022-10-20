package com.example.practica2

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.practica2.databinding.ActivityScrollingBinding
import kotlin.random.Random

class ScrollingActivity : AppCompatActivity() {

    var cc : ArrayList<CentroComercial>? = null
    var tiendas : ArrayList<Tienda>? = null
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tiendas = initTiendas()
        cc = initInfo()

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configCardIntents()
        configGlide()
        configCC(cc!!)
    }

    override fun onStart() {
        mediaPlayer = MediaPlayer.create(this, R.raw.ai_2)
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
    }

    override fun onPause() {
        mediaPlayer?.pause()
        if (mediaPlayer != null) position = mediaPlayer!!.currentPosition
        super.onPause()
    }

    private fun initInfo(): ArrayList<CentroComercial>{
        val cc : ArrayList<CentroComercial> = ArrayList()
        val nombres : ArrayList<String> = ArrayList()
        nombres.add("MN4 Alfafar")
        nombres.add("Bonaire")
        nombres.add("El Saler")
        nombres.add("Arena")
        var i : Int
        for (nombre in nombres){
            val ccl = CentroComercial(nombre, "c/ $nombre", null, ArrayList<Tienda>())
            var j = 0
            i = Random.nextInt(0,tiendas!!.size)
            for (tienda in tiendas!!){
                if (j!=i) {
                    ccl.listaTiendas.add(tienda)
                    j++
                } else break
            }
            ccl.finish()
            cc.add(ccl)
        }
        return cc
    }

    private fun initTiendas(): ArrayList<Tienda>{
        val tiendas : ArrayList<Tienda> = ArrayList()
        tiendas.add(Tienda("Zara","Tienda de ropa."))
        tiendas.add(Tienda("Bershka","Tienda de ropa."))
        tiendas.add(Tienda("Sprinter","Tienda de ropa deportiva."))
        tiendas.add(Tienda("Hollyster","Tienda de ropa cara."))
        tiendas.add(Tienda("100 Montaditos","Restaurante."))
        tiendas.add(Tienda("VIPS","Restaurante."))
        tiendas.add(Tienda("Fnac","Tienda multiproducto."))
        return tiendas
    }

    private fun configCardIntents(){
        binding.include.card1.setOnClickListener {
            startTiendasActivity()
        }

        binding.include.card2.setOnClickListener {
            startTiendasActivity()
        }

        binding.include.card3.setOnClickListener {
            startTiendasActivity()
        }

        binding.include.card4.setOnClickListener {
            startTiendasActivity()
        }
    }
    private fun configGlide(){
        Glide.with(this)
            .load("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0e/1d/3e/d2/centro-comercial-mn4.jpg?w=1200&h=-1&s=1")
            .centerCrop()
            .into(binding.include.img1)

        Glide.with(this)
            .load("https://res.cloudinary.com/westfielddg/image/upload/westfield-media/es/centre/mobile-app/zcarfj4iu8dw4jwqidq6.jpg")
            .centerCrop()
            .into(binding.include.img2)

        Glide.with(this)
            .load("https://infoemplea2.com/wp-content/uploads/2022/05/centro-comercial-el-saler-valencia.jpg")
            .centerCrop()
            .into(binding.include.img3)

        Glide.with(this)
            .load("https://www.lovevalencia.com/wp-content/uploads/2013/03/ARENA-750x400.jpg")
            .centerCrop()
            .into(binding.include.img4)
    }

    private fun configCC(cc : ArrayList<CentroComercial>){
        binding.include.mn4title.text = cc[0].nombre
        binding.include.mn4address.text = cc.get(0).direccion
        binding.include.mn4storenumber.text = cc.get(0).ntiendas.toString()

        binding.include.bonaireTitle.text = cc[1].nombre
        binding.include.bonaireAddress.text = cc.get(1).direccion
        binding.include.bonaireStorenumber.text = cc.get(1).ntiendas.toString()

        binding.include.elsalerTitle.text = cc[2].nombre
        binding.include.elsalerAddress.text = cc.get(2).direccion
        binding.include.elsalerStorenumber.text = cc.get(2).ntiendas.toString()

        binding.include.arenaTitle.text = cc[3].nombre
        binding.include.arenaAddress.text = cc.get(3).direccion
        binding.include.arenaStorenumber.text = cc.get(3).ntiendas.toString()
    }

    private fun startTiendasActivity(){
        val tiendasActivity = Intent(this, TiendasActivity::class.java)
        startActivity(tiendasActivity)
    }

    public fun getPosition(): Int{
        return position
    }
}