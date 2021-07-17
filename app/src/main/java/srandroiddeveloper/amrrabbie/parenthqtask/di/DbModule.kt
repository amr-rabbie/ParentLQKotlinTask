package srandroiddeveloper.amrrabbie.parenthqtask.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDao
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDbInstance(application: Application):WeatherDb=
        Room.databaseBuilder(application.applicationContext,
        WeatherDb::class.java,
        "weatherdb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDbDao(weatherDb: WeatherDb):WeatherDao=
        weatherDb.weatherdao()
}