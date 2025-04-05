package com.example.myflighttrackerapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainViewModel: ViewModel() {
    var flightList by mutableStateOf<List<FlightData>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var searchQuery by mutableStateOf("")


    //retrofit instance//
    private val api = Retrofit.Builder()
        .baseUrl("https://api.aviationstack.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FlightApiServiceInterface::class.java)


    fun loadAllFlights(apiKey: String){
        isLoading = true
        error=""
        viewModelScope.launch {
            try{
                val response = api.getFlightData(apiKey, "")
                flightList = response.data!!
                Log.d("FlightAPI", "Fetched ${flightList.size} flights")
                Log.d("FlightAPI", "Full response: ${Gson().toJson(response)}")

            }catch(e: Exception){
                error = "Failed to load flights: ${e.message}"
                flightList = emptyList()

            }finally {
                isLoading = false
            }
        }
    }

    fun filteredFlights(): List<FlightData>{
        return if(searchQuery.isBlank()){
            flightList
        }else{
            flightList.filter {
                it.flight.iata.contains(searchQuery, ignoreCase = true)
            }
        }

    }

}