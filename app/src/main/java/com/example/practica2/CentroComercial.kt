package com.example.practica2

import java.lang.Exception

data class CentroComercial(
    var nombre : String,
    var direccion : String,
    var ntiendas : Int?,
    var listaTiendas : ArrayList<Tienda> ) {

    fun finish(){
        this.ntiendas = listaTiendas.size
    }
}