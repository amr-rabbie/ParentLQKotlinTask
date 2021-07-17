package srandroiddeveloper.amrrabbie.parenthqtask.dp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Weather::class,WeatherDetails::class],version = 1)
abstract  class WeatherDb :RoomDatabase(){
    abstract fun weatherdao():WeatherDao
}