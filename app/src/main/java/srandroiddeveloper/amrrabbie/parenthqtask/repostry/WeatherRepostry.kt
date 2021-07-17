package srandroiddeveloper.amrrabbie.parenthqtask.repostry

import androidx.lifecycle.LiveData
import srandroiddeveloper.amrrabbie.parenthqtask.dp.Weather
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDao
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDetails
import srandroiddeveloper.amrrabbie.parenthqtask.network.WeatherApiService
import javax.inject.Inject

class WeatherRepostry
@Inject
constructor(private val weatherApiService: WeatherApiService,private val weatherDao: WeatherDao){

    suspend fun getWeatherDataByCity(q:String,apikey:String)=
        weatherApiService.getWeatherDataByCity(q,apikey)

    suspend fun getWeatherDataByLocation(lat:Double,lon:Double,apikey:String)=
        weatherApiService.getWeatherDataByLocation(lat,lon,apikey)

    fun insertWeatherData(weather: Weather)=
        weatherDao.insertWeatherData(weather)

    fun insertDetailsData(weatherDetails: WeatherDetails)=
        weatherDao.insertDetailsData(weatherDetails)

    fun deleteWeatherData(name:String)=
        weatherDao.deleteWeatherData(name)

    fun deleteDetailsData(name: String)=
        weatherDao.deleteDetailsData(name)

    fun getWeatherDataDetails(name: String):LiveData<List<WeatherDetails>>{
        return weatherDao.getWeatherDataDetails(name)
    }

    fun getWeatherDataOffline():LiveData<List<Weather>>{
        return weatherDao.getWeatherDataOffline()
    }

    fun checkIsExists(name: String):Int=
        weatherDao.checkIsExists(name)

    fun checkIsMaxFive():Int=
        weatherDao.checkIsMaxFive()
}