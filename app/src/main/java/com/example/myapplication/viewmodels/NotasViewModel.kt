package com.example.myapplication.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.myapplication.states.NotasState
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.example.myapplication.room.NotaDatabaseDao
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.myapplication.models.Notas


class NotasViewModel(
    private val dao: NotaDatabaseDao
) : ViewModel() {
    var state by mutableStateOf(NotasState())



    init { obtenerNotasFiltro(false) }

    fun agregarNota(nota: Notas) = viewModelScope.launch {
        dao.agregarNota(nota = nota)

    }

    fun actualizarNota(nota: Notas) = viewModelScope.launch {
        dao.actualizarNota(nota = nota)
    }

    fun borrarNota(nota: Notas) = viewModelScope.launch {
        dao.borrarNota(nota = nota)
    }

    fun obtenerNotasFiltro(filtro: Boolean, completadas: Boolean = false) = viewModelScope.launch {
        val notasFiltro = if (filtro) {
            dao.obtenerNotasFiltro(completadas)
        } else {
            dao.obtenerNotas()
        }

        notasFiltro.collectLatest { notas ->
            state = NotasState(listaNotas = notas, isEmpty = notas.isEmpty())
        }

    }

    }

