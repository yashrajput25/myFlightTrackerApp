package com.example.myflighttrackerapp



data class FlightResponse(
    val data: List<FlightData>
)

data class FlightData(
    val flight: FLightInfo,
    val live: LiveData?
)

data class FLightInfo(
    val iata: String
)

data class LiveData(
    val latitude: Double?,
    val longitude: Double?,
    val altitude: Double?,
    val speed_horizontal: Double?

)