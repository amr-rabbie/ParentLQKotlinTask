package srandroiddeveloper.amrrabbie.parenthqtask.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import srandroiddeveloper.amrrabbie.parenthqtask.model.WeatherApiResponse
import srandroiddeveloper.amrrabbie.parenthqtask.utils.Constants

interface WeatherApiService {

    @GET(Constants.End_Pont)
    suspend fun getWeatherDataByCity(
        @Query("q") q:String,
        @Query("appid") appid:String
    ):Response<WeatherApiResponse>

    @GET(Constants.End_Pont)
    suspend fun getWeatherDataByLocation(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") appid:String
    ):Response<WeatherApiResponse>


}