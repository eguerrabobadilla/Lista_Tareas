package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.myapplication.navigation.NavManager
import com.example.myapplication.room.NotasDatabase
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodels.NotasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            MyApplicationTheme( darkTheme = darkTheme ) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val database = Room.databaseBuilder(this, NotasDatabase::class.java, "db_notas").build()
                    val dao = database.notasDao()

                    val viewModel = NotasViewModel(dao)

                    NavManager(viewModel = viewModel, onThemeUpdated = { darkTheme = !darkTheme })
                }
            }
        }
    }

}

