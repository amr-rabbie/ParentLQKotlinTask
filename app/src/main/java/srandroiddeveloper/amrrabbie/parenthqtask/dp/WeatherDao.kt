package srandroiddeveloper.amrrabbie.parenthqtask.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WeatherDao {

    @Insert
    fun insertWeatherData(weather: Weather)
    @Insert
    fun insertDetailsData(details: WeatherDetails)

    @Query("delete from weather_table where city=:name")
    fun deleteWeatherData(name:String)
    @Query("delete from details_table where city=:name")
    fun deleteDetailsData(name:String)

    @Query("select * from details_table where city=:name")
    fun getWeatherDataDetails(name:String):LiveData<List<WeatherDetails>>
    @Query("select * from weather_table")
    fun getWeatherDataOffline():LiveData<List<Weather>>

    @Query("select count(*) from weather_table where city=:name")
    fun checkIsExists(name:String):Int

    @Query("select count(*) from weather_table")
    fun checkIsMaxFive():Int
}