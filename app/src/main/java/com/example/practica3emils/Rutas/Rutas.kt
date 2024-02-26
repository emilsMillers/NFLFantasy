package com.example.practica3emils.Rutas

sealed class Rutas(val ruta: String) {

    object MenuPrincipal: Rutas("MenuPrincipal")
    object AñadirJugador: Rutas("AñadirJugador")
    object InformacionJugador: Rutas("InformacionJugador")

}