package com.example.myapplication.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.models.Notas
import com.example.myapplication.viewmodels.NotasViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarView(navController: NavController, viewModel: NotasViewModel, id: Int, nota: String?, descripcion: String?, completada: Boolean) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Editar View", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                }
            )
        }
    ) {
        ContentEditarView(it, navController, viewModel, id, nota, descripcion, completada)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditarView(it: PaddingValues, navController: NavController, viewModel: NotasViewModel, id: Int, nota: String?, descripcion: String?, completada: Boolean) {
    var nota by remember { mutableStateOf(nota) }
    var descripcion by remember { mutableStateOf(descripcion) }
    var completada by remember { mutableStateOf(completada) }


    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nota ?: "",
            onValueChange = { nota = it },
            label = { Text(text = "Título") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = descripcion ?: "",
            onValueChange = { descripcion = it },
            label = { Text(text = "Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        // CheckBox debajo de la descripción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

                Checkbox(

                    checked =completada,
                    onCheckedChange = { completada= it }
                )

            Text(text = "Marcar como completada", modifier = Modifier.weight(1f))
        }

        Button(
            onClick = {
                val nota = Notas(id = id, nota = nota!!, descripcion = descripcion!!, fecha = LocalDate.now().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")), completada = completada)

                viewModel.actualizarNota(nota)
                navController.popBackStack()
            }
        ) {
            Text(text = "Editar")
        }
    }
}