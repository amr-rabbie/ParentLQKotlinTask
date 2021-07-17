package srandroiddeveloper.amrrabbie.parenthqtask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import srandroiddeveloper.amrrabbie.parenthqtask.dp.Weather
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDetails
import srandroiddeveloper.amrrabbie.parenthqtask.model.WeatherApiResponse
import srandroiddeveloper.amrrabbie.parenthqtask.repostry.WeatherRepostry
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val weatherRepostry: WeatherRepostry):ViewModel(){

    var weatherdata=MutableLiveData<WeatherApiResponse>()

    lateinit var weatherdetailslist:LiveData<List<WeatherDetails>>

    lateinit var weatherlist:LiveData<List<Weather>>



    fun getWeatherDataFromCity(q:String,apikey:String)=viewModelScope.launch {
        weatherRepostry.getWeatherDataByCity(q,apikey).let { response->
            if(response.isSuccessful){
                weatherdata.postValue(response.body())
            }else{
                Log.d("TAG", "getWeatherDataFromCity: ${response.message()}")
            }
        }
    }

    fun getWeatherDataFromLocation(lat:Double,lon:Double,apikey:String)=viewModelScope.launch {
        weatherRepostry.getWeatherDataByLocation(lat,lon,apikey).let { response->
            if(response.isSuccessful){
                weatherdata.postValue(response.body())
            }else{
                Log.d("TAG", "getWeatherDataFromLocation: "+response.message())
            }
        }
    }

    public fun insertWeatherData(weather: Weather)=
        weatherRepostry.insertWeatherData(weather)


    public fun insertDetailsData(weatherDetails: WeatherDetails)=
        weatherRepostry.insertDetailsData(weatherDetails)

    public fun deleteWeatherData(name:String)=
        weatherRepostry.deleteWeatherData(name)

    public fun deleteDetailsData(name: String)=
        weatherRepostry.deleteDetailsData(name)

    fun getWeatherDataDetails(name: String){
        weatherdetailslist=weatherRepostry.getWeatherDataDetails(name)
    }

    fun getWeatherDataOffline(){
        weatherlist=weatherRepostry.getWeatherDataOffline()
    }

    public fun checkIsExists(name: String):Int{
        return weatherRepostry.checkIsExists(name)
    }

    public fun checkIsMaxFive():Int{
        return weatherRepostry.checkIsMaxFive()
    }
}
