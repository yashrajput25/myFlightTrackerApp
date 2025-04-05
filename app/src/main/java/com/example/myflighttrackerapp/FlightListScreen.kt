package com.example.myflighttrackerapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlightListScreen(viewModel: MainViewModel) {
    val flights = viewModel.filteredFlights()
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.searchQuery = it },
            label = { Text("Search by Flight Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> CircularProgressIndicator()
            error.isNotBlank() -> Text("Error: $error", color = MaterialTheme.colorScheme.error)
            else -> LazyColumn {
                items(flights) { flight ->
                    FlightItemCard(flight)
                }
            }
        }
    }
}

@Composable
fun FlightItemCard(flight: FlightData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Flight: ${flight.flight.iata}")
            Text("Latitude: ${flight.live?.latitude ?: "N/A"}")
            Text("Longitude: ${flight.live?.longitude ?: "N/A"}")
            Text("Altitude: ${flight.live?.altitude ?: "N/A"}")
            Text("Speed: ${flight.live?.speed_horizontal ?: "N/A"}")
        }
    }
}
