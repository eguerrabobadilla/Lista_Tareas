package com.example.myapplication.states

import com.example.myapplication.models.Notas

data class NotasState(
    val listaNotas: List<Notas> = emptyList(),
    val isEmpty: Boolean = false
)
