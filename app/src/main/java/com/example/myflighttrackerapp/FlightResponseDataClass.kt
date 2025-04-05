package com.example.myflighttrackerapp

data class FlightResponse(
    val data: List<FlightData>?
)

data class FlightData(
    val flight: FlightInfo,
    val live: LiveData?
)

data class FlightInfo(
    val iata: String
)

data class LiveData(
    val latitude: Double?,
    val longitude: Double?,
    val altitude: Double?,
    val speed_horizontal: Double?

)