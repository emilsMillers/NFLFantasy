package com.example.practica3emils.Ventanas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.practica3emils.Datos.ArrayBox
import com.example.practica3emils.Datos.ImagenJugador
import com.example.practica3emils.Datos.obtenerColorEquipo

@Composable
fun informacionJugador() {
    var jugador = ArrayBox[num]
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = obtenerColorEquipo(jugador.equipo)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${jugador.nombre} jugador de ${jugador.equipo}", color = Color.White)
        Image(
            painter = painterResource(id = ImagenJugador(jugador.nombre)),
            contentDescription = null,
            modifier = Modifier.size(500.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Nombre: ${jugador.nombre} \nEquipo: ${jugador.equipo}\nPosición:  " +
                    "${jugador.posiciones}\nPuntos: ${jugador.goles}\nPartido contra: " +
                    "${jugador.equipoContra}\nDia: ${jugador.fecha}",
            color = Color.White
        )
    }
}