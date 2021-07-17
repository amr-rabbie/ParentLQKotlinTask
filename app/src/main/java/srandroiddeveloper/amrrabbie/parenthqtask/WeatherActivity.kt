package srandroiddeveloper.amrrabbie.parenthqtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import srandroiddeveloper.amrrabbie.parenthqtask.adapters.WeatherAdapter
import srandroiddeveloper.amrrabbie.parenthqtask.adapters.WeatherOfflineAdapter
import srandroiddeveloper.amrrabbie.parenthqtask.databinding.ActivityWeatherBinding
import srandroiddeveloper.amrrabbie.parenthqtask.dp.Weather
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDetails
import srandroiddeveloper.amrrabbie.parenthqtask.model.ListItem
import srandroiddeveloper.amrrabbie.parenthqtask.model.WeatherApiResponse
import srandroiddeveloper.amrrabbie.parenthqtask.viewmodel.WeatherViewModel

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity()  {

    lateinit var binding: ActivityWeatherBinding
    val weatherViewModel:WeatherViewModel by viewModels()
    lateinit var weatherAdapter: WeatherAdapter
    lateinit var weatherOfflineAdapter: WeatherOfflineAdapter
    lateinit var city:String
    lateinit var weatherdata: WeatherApiResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var wintent=intent
        if(wintent.hasExtra("City") && wintent.hasExtra("Weather")){
            city= wintent.getStringExtra("City").toString()
            binding.txtcity.text=city
            weatherdata= wintent.getParcelableExtra("Weather")!!

            if(weatherdata != null){
                bindWeatherData()
            }
        }else{
            city= wintent.getStringExtra("City").toString()

            bindDataOffline()
        }

        binding.btnsave.setOnClickListener {mview->
            saveDataLocal()
        }
    }

    private fun bindDataOffline() {
        binding.txtcity.text=city
        binding.btnsave.visibility=View.GONE
        weatherViewModel.getWeatherDataDetails(city)

        weatherViewModel.weatherdetailslist.observe(this,{response->
            if(response != null){
                bindWeatherDataOffline(response)
            }
        })
    }

    private fun bindWeatherDataOffline(response: List<WeatherDetails>) {
        weatherOfflineAdapter= WeatherOfflineAdapter(this,response)

        binding.weatherrecycler.apply {
            layoutManager=LinearLayoutManager(this@WeatherActivity)
            adapter=weatherOfflineAdapter
        }
    }


    private fun bindWeatherData() {
        var weatherlist=weatherdata.list
        if(weatherlist != null){
            weatherAdapter= WeatherAdapter(this,weatherlist)

            binding.weatherrecycler.apply {
                layoutManager=LinearLayoutManager(this@WeatherActivity)
                adapter=weatherAdapter
            }
        }
    }

    private fun saveDataLocal() {
        if(weatherdata != null) {
            var isExists:Int=weatherViewModel.checkIsExists(city)
            if(isExists <= 0){
                var isMaxfive:Int=weatherViewModel.checkIsMaxFive()
                if(isMaxfive <5){
                    saveData()
                }else{
                    Toast.makeText(this,"Mamimum favourite locations is five only",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"This location added before",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun saveData() {
        var weatherlist=weatherdata.list

        var weather=Weather(city)
        weatherViewModel.insertWeatherData(weather)

        for(item in weatherlist){

            var weatherdetails=WeatherDetails(city,item.dtTxt,item.weather[0].description,
            item.main.temp.toString(),item.main.tempMin.toString(),item.main.tempMax.toString(),
            item.main.humidity.toString(),item.main.pressure.toString(),item.wind.speed.toString(),
            item.wind.deg.toString(),item.clouds.all.toString(),item.visibility.toString())

            weatherViewModel.insertDetailsData(weatherdetails)

            Toast.makeText(this,"Location data added to favourite",Toast.LENGTH_LONG).show()

        }
    }
}