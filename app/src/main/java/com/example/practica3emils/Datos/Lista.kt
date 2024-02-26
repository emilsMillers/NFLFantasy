package com.example.practica3emils.Datos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.practica3emils.R
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun lista( lista : ArrayList<String>,recogerTexto : (String)-> Unit){
    var texto by remember { mutableStateOf("") }
    val opciones = lista
    var expandir by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expandir, onExpandedChange = {expandir = !expandir} , modifier = Modifier.fillMaxWidth()) {
        TextField(value = texto,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            label = { Text("Seleccionar opcion") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandir) }
        )
        DropdownMenu(expanded = expandir , onDismissRequest = { expandir = false }) {
            opciones.forEach { elemento ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(),
                    text = { Text(elemento) },
                    onClick = { texto = elemento;recogerTexto(
                        elemento
                    )}

                )
            }

        }
    }


}

@SuppressLint("ComposableNaming")
@Composable
fun radioBoton(Posicion : String,valorPosicion : (String) -> Unit , textoTotalPos : String){
    var Pulsado by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
        RadioButton(selected = Pulsado, onClick = { Pulsado = !Pulsado;
            if (Pulsado){
                if (textoTotalPos.contains("$Posicion")){
                    valorPosicion("$textoTotalPos")
                }else{valorPosicion("$textoTotalPos $Posicion")}
            }
            else{
                if (textoTotalPos.contains("$Posicion")){
                    val textoTotalPos = textoTotalPos.replace("$Posicion","")
                    valorPosicion("$textoTotalPos")
                }
            }})
        Text(text = Posicion)
    }
}

@Composable
fun ImagenJugador(nombre: String): Int {
    return when (nombre) {
        "Josh Allen" -> R.drawable.josh
        "Ja'Marr Chase" -> R.drawable.jamar
        "Micah Parsons" -> R.drawable.micah
        "Joe Flacco" -> R.drawable.joe
        "Travis Kelce" -> R.drawable.travis
        "Austin Ekeler" -> R.drawable.austin
        "Tyreek Hill" -> R.drawable.tyreek
        "Jarret Stidham" -> R.drawable.jarret
        "DeVonta Smith" -> R.drawable.devo
        "Breece Hall" -> R.drawable.breece
        else -> R.drawable.nfl
    }
}

fun obtenerColorEquipo(nombreEquipo: String): Color {
    return when (nombreEquipo) {
        "Buffalo Bills" -> Color.Blue
        "Cincinnati Bengals" -> Color(0xFFFC4004)
        "Dallas Cowboys" -> Color(0xFF010D38)
        "Cleveland Browns" -> Color(0xFFB95304)
        "Kansas City Chiefs" -> Color(0xFFC20303)
        "Los Angeles Chargers" -> Color(0xFFF7CC08)
        "Miami Dolphins" -> Color(0xFF2BA293)
        "Denver Broncos" -> Color(0xFFCA6A03)
        "Philadelphia Eagles" -> Color(0xFF124921)
        "New York Jets" -> Color(0xFF1F9E09)
        else -> Color.Gray
    }
}

fun convertirMillisAFecha(millis: Long): String {
    val sdf = android.icu.text.SimpleDateFormat("dd/MM/yyyy")
    val fecha = Date(millis)
    return sdf.format(fecha)
}