package com.example.myapplication.views


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.viewmodels.NotasViewModel


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController: NavController, viewModel: NotasViewModel , onThemeUpdated: () -> Unit) {
var expanded by remember { mutableStateOf(false) }
    val menuExpanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {

            TopAppBar(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                    .height(80.dp),
                title = {
                    Text(
                        text = "Lista de Tareas",
                         color = Color.White,
                         fontWeight = FontWeight.Bold,
                         modifier = Modifier
                            .padding(top = 15.dp)
                    )
                },

                actions = {
                    IconButton(
                        onClick = {
                            menuExpanded.value = true

                        }
                    ) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }

                    Column(
                        modifier = Modifier
                    ) {
                        DropdownMenu(
                            expanded = menuExpanded.value,
                            onDismissRequest = {
                                menuExpanded.value = false
                            },
                            modifier = Modifier.width(200.dp)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Todas las Tareas") },
                                onClick = {
                                            viewModel.obtenerNotasFiltro(false,false)
                                             menuExpanded.value=false
                                          },

                            )
                            DropdownMenuItem(
                                text = { Text("Tareas Completadas") },
                                onClick = {
                                    viewModel.obtenerNotasFiltro(true,true)
                                    menuExpanded.value=false
                                },
                            )
                            DropdownMenuItem(
                                text = { Text("Tareas no Completadas") },
                                onClick = {
                                    viewModel.obtenerNotasFiltro(true,false)
                                    menuExpanded.value=false
                                },
                            )

                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),



            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            FloatingActionButton(
                onClick = { onThemeUpdated() },
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Bottom).padding(start = 25.dp),
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "tema")
            }
            FloatingActionButton(
                onClick = { navController.navigate("agregar") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
         }
        }
    ) {
        ContentInicioView(it, navController, viewModel)
    }
}

@Composable
fun ContentInicioView(it: PaddingValues, navController: NavController, viewModel: NotasViewModel) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tu lista de tareas está vacía.",
            modifier = Modifier.alpha(if (state.isEmpty) 1.0f else 0.0f)
        )
    }

    Column(
        modifier = Modifier.padding(it)
    ) {
        LazyColumn {
            items(state.listaNotas) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { navController.navigate("editar/${it.id}/${it.nota}/${it.descripcion}/${it.completada}") },
                    shape = RoundedCornerShape(16.dp)

                ) {

                    // Columna principal
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = it.nota,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.descripcion,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        // Columna para fecha y botones
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Row(
                                modifier = Modifier
                                .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {

                                Text(
                                    textAlign = TextAlign.Start,
                                    text = it.fecha,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                      if (it.completada)
                                        Icon(imageVector = Icons.Default.Check, contentDescription = "Editar",
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                        )


                                    IconButton(
                                        onClick = { viewModel.borrarNota(it) },
                                    ) {
                                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar",
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}