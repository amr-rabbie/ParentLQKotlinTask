package srandroiddeveloper.amrrabbie.parenthqtask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import srandroiddeveloper.amrrabbie.parenthqtask.network.WeatherApiService
import srandroiddeveloper.amrrabbie.parenthqtask.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance():WeatherApiService=
        Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)

}