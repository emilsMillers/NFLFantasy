package com.example.practica3emils.Datos

import java.util.Random

var ArrayBox = ArrayList<DatosJugador>()

class DatosArray() {
    val opcionesJugador = arrayListOf(
        "Josh Allen",
        "Ja'Marr Chase",
        "Micah Parsons",
        "Joe Flacco",
        "Travis Kelce",
        "Austin Ekeler",
        "Tyreek Hill",
        "Jarret Stidham",
        "DeVonta Smith",
        "Breece Hall"
    )

    val opcionesEquipo = arrayListOf(
        "Buffalo Bills",
        "Cincinnati Bengals",
        "Dallas Cowboys",
        "Cleveland Browns",
        "Kansas City Chiefs",
        "Los Angeles Chargers",
        "Miami Dolphins",
        "Denver Broncos",
        "Philadelphia Eagles",
        "New York Jets"
    )
    fun crearJugadores() {
        val datos = DatosArray()
        val random = Random()

        for (i in datos.opcionesJugador.indices) {
            val nombre = datos.opcionesJugador[i]
            val equipo = datos.opcionesEquipo[i]
            val posicion = listOf("QB", "RB", "FB", "G", "K", "SS", "C", "TB").random()
            val goles = random.nextInt(10)
            val equipoContra = datos.opcionesEquipo[random.nextInt(datos.opcionesEquipo.size)]
            val fecha = "${random.nextInt(28) + 1}/${random.nextInt(12) + 1}/${random.nextInt(5) + 2020}" // Fecha aleatoria entre 2020 y 2024

            datos.Datos(nombre, equipo, posicion, goles, equipoContra, fecha)
        }
    }

    fun Datos(
        nombre: String,
        equipo: String,
        posicones: String,
        goles: Int,
        equipoContra: String,
        fecha: String
    ) {
        var dato = DatosJugador(nombre, equipo, posicones, goles, equipoContra, fecha)
        ArrayBox.add(dato)
        println("  " + ArrayBox.size + "inside")
    }
}