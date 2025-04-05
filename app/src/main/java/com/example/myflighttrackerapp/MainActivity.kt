package com.example.myflighttrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myflighttrackerapp.ui.theme.MyFlightTrackerAppTheme


class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MyFlightTrackerAppTheme {
                // Replace with your actual API key
                val apiKey = "7xxx"


                // Fetch all flights when the screen starts
                LaunchedEffect(Unit) {
                    viewModel.loadAllFlights(apiKey)
                }

                // Show flight list with search bar
                FlightListScreen(viewModel)
            }


        }
    }
}
