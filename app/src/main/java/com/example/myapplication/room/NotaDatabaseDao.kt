package com.example.myapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import kotlinx.coroutines.flow.Flow
import com.example.myapplication.models.Notas


@Dao
interface NotaDatabaseDao {

    @Query("SELECT * FROM notas")
    fun obtenerNotas(): Flow<List<Notas>>

    @Query("SELECT * FROM notas where completada = :completada")
    fun obtenerNotasFiltro(completada: Boolean): Flow<List<Notas>>

    @Query("SELECT * FROM notas WHERE id = :id")
    fun obtenerNota(id: Int): Flow<Notas>

    @Insert
    suspend fun agregarNota(nota: Notas)

    @Update
    suspend fun actualizarNota(nota: Notas)

    @Delete
    suspend fun borrarNota(nota: Notas)

}

