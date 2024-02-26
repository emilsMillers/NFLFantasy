package com.example.practica3emils.Ventanas

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica3emils.Datos.ArrayBox
import com.example.practica3emils.Datos.DatosArray
import com.example.practica3emils.Datos.DatosJugador
import com.example.practica3emils.Datos.ImagenJugador
import com.example.practica3emils.Datos.obtenerColorEquipo
import com.example.practica3emils.R
import com.example.practica3emils.Rutas.Rutas

var num : Int = 0;
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NotConstructor")
@Composable
fun MenuPrincipal(navController: NavController) {
    DatosArray().crearJugadores()
    var textoSearchBar by remember { mutableStateOf("") }
    var activeSearchBar by remember { mutableStateOf(false) }
    var listaMutable = remember { mutableStateListOf<DatosJugador>().apply { addAll(ArrayBox) } }
    var botonCheck by remember { mutableStateOf(false) }
    var borrar by remember { mutableStateOf(false) }
    var Borrar = ArrayList<Int>()
    var filtro by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "La NFL fantasy de tus sueños",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = textoSearchBar,
            onQueryChange = { textoSearchBar = it },
            onSearch = { activeSearchBar = false },
            active = activeSearchBar,
            onActiveChange = { activeSearchBar = it }) {
            val filtrarEquipos =
                DatosArray().opcionesEquipo.filter { it.contains(textoSearchBar, true) }
            for (i in filtrarEquipos.indices) {
                Button(
                    onClick = { filtro = "${filtrarEquipos[i]}" },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "${filtrarEquipos[i]}")
                }
            }
        }
        LazyColumn() {
            items(listaMutable) { jugador ->
                if (textoSearchBar == "" || "${jugador.equipo}" == filtro) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(obtenerColorEquipo(jugador.equipo))
                    ) {
                        Column {
                            var CheckPulsado by remember { mutableStateOf(false) }
                            var habilitado by remember { mutableStateOf(true) }
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    painterResource(id = ImagenJugador(jugador.nombre)),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(90.dp)
                                        .padding(8.dp)
                                )
                                Column(modifier = Modifier.weight(3f)) {
                                    Text(
                                        text = "${jugador.nombre}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = " Vs ${jugador.equipoContra} , ${jugador.fecha}",
                                            textAlign = TextAlign.Center
                                        )
                                        if (botonCheck) {
                                            Checkbox(
                                                checked = CheckPulsado,
                                                onCheckedChange = {
                                                    CheckPulsado = !CheckPulsado
                                                },
                                                enabled = habilitado,
                                                modifier = Modifier
                                            )
                                            if (CheckPulsado) {
                                                Borrar.add(listaMutable.indexOf(jugador))
                                            }
                                        }
                                    }
                                    Text(
                                        text = "${jugador.goles}",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                            Button(onClick = {
                                num = listaMutable.indexOf(jugador)
                                navController?.navigate(Rutas.InformacionJugador.ruta)
                            }) {
                                Text(text = "Informacion")
                            }
                        }
                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        ExtendedFloatingActionButton(
            onClick = { navController.navigate(Rutas.AñadirJugador.ruta) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Añadir")
            Icon(
                painterResource(id = android.R.drawable.ic_menu_add),
                contentDescription = null,
                modifier = Modifier.size(27.dp)
            )
        }
        ExtendedFloatingActionButton(
            onClick = {
                botonCheck = !botonCheck
                borrar = true
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Borrar")
            Icon(
                painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = null
            )
        }
    }
}