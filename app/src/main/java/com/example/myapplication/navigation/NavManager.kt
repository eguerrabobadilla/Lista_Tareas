package com.example.myapplication.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.viewmodels.NotasViewModel
import com.example.myapplication.views.AgregarView
import com.example.myapplication.views.EditarView
import com.example.myapplication.views.InicioView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavManager(viewModel: NotasViewModel, onThemeUpdated: () -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            InicioView(navController, viewModel ,onThemeUpdated)

            onThemeUpdated
        }

        composable("agregar") {
            AgregarView(navController, viewModel)
        }

        composable("editar/{id}/{nota}/{descripcion}/{completada}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("nota") { type = NavType.StringType },
            navArgument("descripcion") { type = NavType.StringType },
            navArgument("completada") { type = NavType.BoolType },




        )) {
            EditarView(
                navController,
                viewModel,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("nota"),
                it.arguments?.getString("descripcion"),
                it.arguments!!.getBoolean("completada"),
            )
        }
    }

}