package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "notas")
data class Notas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("nota")
    val nota: String,
    @ColumnInfo("descripcion")
    val descripcion: String,
    @ColumnInfo("fecha")
    val fecha: String,
    @ColumnInfo("completada")
    val completada: Boolean = false
)
