package com.example.practica3emils.Nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica3emils.Rutas.Rutas
import com.example.practica3emils.Ventanas.AñadirJugador
import com.example.practica3emils.Ventanas.MenuPrincipal
import com.example.practica3emils.Ventanas.informacionJugador

@Composable
fun GrafoNavegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.MenuPrincipal.ruta) {

        // "URL" -> Composable
        composable(Rutas.MenuPrincipal.ruta){
            MenuPrincipal(navController = navController)
        }

        composable(Rutas.AñadirJugador.ruta){
            AñadirJugador(navController = navController)
        }

        composable(Rutas.InformacionJugador.ruta){
            informacionJugador()
        }

    }
}