package com.example.practica3emils.Ventanas

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.practica3emils.Datos.DatosArray
import com.example.practica3emils.Datos.convertirMillisAFecha
import com.example.practica3emils.Datos.lista
import com.example.practica3emils.Datos.radioBoton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AñadirJugador(navController: NavController){
    var fecha by remember { mutableStateOf("") }
    var recogerTextoJugador by remember { mutableStateOf("") }
    var recogerTextoEquipo by remember { mutableStateOf("") }
    var recogerTextoPosicion by remember { mutableStateOf("") }
    var recogerTextoEquipoContra by remember { mutableStateOf("") }
    var movimientoSlider by remember { mutableFloatStateOf(0f) }
    val opcionesEquipo = DatosArray().opcionesEquipo
    val opcionesJugador = DatosArray().opcionesJugador
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "Introduce el nombre del jugador:")
        lista(lista = opcionesJugador, recogerTexto = {nuevoValor -> recogerTextoJugador = nuevoValor})
        Text(text = "Introduce el equipo en el que juega:")
        lista(lista = opcionesEquipo, recogerTexto = {nuevoValor -> recogerTextoEquipo = nuevoValor})
        Text(text = "Selecciona una o mas posiciones")
        radioBoton(Posicion = "QB", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "RB", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "FB", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "G", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "K", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "SS", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "C", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        radioBoton(Posicion = "TB", { nuevoValor -> recogerTextoPosicion = nuevoValor } , textoTotalPos = recogerTextoPosicion)
        recogerTextoPosicion = recogerTextoPosicion.trim()
        Text(text = "El numero de puntos que hizo en el partido")
        Slider(value = movimientoSlider,
            onValueChange = {movimientoSlider = it},
            valueRange = 0f..20f, steps = 20)
        Text("${movimientoSlider.toInt()}")
        Text(text = "El equipo contra el que jugo:")
        lista(lista = opcionesEquipo, recogerTexto = {nuevoValor -> recogerTextoEquipoContra = nuevoValor})
        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        DatePicker(state = state,
            modifier = Modifier,
            title = {
                Row {
                    Text(text = "Seleccionar la fecha del partido")
                }
            })
        BackHandler {

            fecha = state.selectedDateMillis?.let { convertirMillisAFecha(it) }.toString()

            DatosArray().Datos(recogerTextoJugador,recogerTextoEquipo,recogerTextoPosicion,movimientoSlider.toInt(),recogerTextoEquipoContra,fecha)
            navController.popBackStack()
        }


    }


}