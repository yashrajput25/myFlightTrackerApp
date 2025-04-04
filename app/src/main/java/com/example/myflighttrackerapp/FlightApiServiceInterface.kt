package com.example.myflighttrackerapp
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightApiServiceInterface{
    @GET("flights")
    fun getFlightData(
        @Query("access_key") apiKey: String,
        @Query("flight_Data") flightNumber: String
    ): FlightResponse
}