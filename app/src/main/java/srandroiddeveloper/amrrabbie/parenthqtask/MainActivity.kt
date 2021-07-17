package srandroiddeveloper.amrrabbie.parenthqtask

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androiddeveloper.amrrabbie.kotlinapimps.utils.Network
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import srandroiddeveloper.amrrabbie.parenthqtask.adapters.FavouritesAdapter
import srandroiddeveloper.amrrabbie.parenthqtask.databinding.ActivityMainBinding
import srandroiddeveloper.amrrabbie.parenthqtask.utils.Constants
import srandroiddeveloper.amrrabbie.parenthqtask.utils.GpsTracker
import srandroiddeveloper.amrrabbie.parenthqtask.viewmodel.WeatherViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    val weatherViewModel:WeatherViewModel by viewModels()
    lateinit var favadapter: FavouritesAdapter

    private var gpsTracker: GpsTracker? = null
    private var latitude = 0.0
    private var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncitysearch.setOnClickListener(this)
        binding.btnlocsearch.setOnClickListener(this)

        bindFavLocations()
    }

    private fun bindFavLocations() {
        weatherViewModel.getWeatherDataOffline()

        weatherViewModel.weatherlist.observe(this, { response ->
            favadapter= FavouritesAdapter(this,response,weatherViewModel)

            binding.favcitiesrecycler.apply {
                layoutManager=LinearLayoutManager(this@MainActivity)
                adapter=favadapter
            }
        }
        )
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if(v.id == R.id.btncitysearch){
                searchByCity()
            }else if(v.id == R.id.btnlocsearch){
                searchByLocation()
            }
        }
    }



    private fun searchByCity() {
        var city:String=binding.txtsearch.text.toString()

        if(city != null){
            weatherViewModel.getWeatherDataFromCity(city,Constants.Api_Key)
            weatherViewModel.weatherdata.observe(this,{response->
                if(response != null){
                    var intent:Intent=Intent(this,WeatherActivity::class.java)
                    intent.putExtra("Weather",response)
                    intent.putExtra("City",city)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Enter another city name",Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this,"You must enter city name",Toast.LENGTH_LONG).show()
        }
    }

    private fun searchByLocation() {
        if (!checkPermission()) {
            requestPermission()
        } else {
            getLocation()
            if(longitude > 0 && latitude > 0){
                weatherViewModel.getWeatherDataFromLocation(latitude,longitude,Constants.Api_Key)
                weatherViewModel.weatherdata.observe(this,{response->
                    if(response != null){
                        var intent:Intent=Intent(this,WeatherActivity::class.java)
                        intent.putExtra("Weather",response)
                        var location:String="$latitude - $longitude"
                        intent.putExtra("City",location)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Gps failed to detect your location",Toast.LENGTH_LONG).show()
                    }
                })
            }else{
                Toast.makeText(this,"Gps failed to detect your location",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getLocation() {
        latitude = 30.0135812
        longitude = 31.2819673
        if (!Network.isNetworkAvailable(this@MainActivity)) {
            latitude = 30.0135812
            longitude = 31.2819673
            return
        } else {
            gpsTracker = GpsTracker(this@MainActivity)
            if (gpsTracker!!.canGetLocation()) {
                latitude = gpsTracker!!.getLatitude()
                longitude = gpsTracker!!.getLongitude()
            } else {
                gpsTracker!!.showSettingsAlert()
            }
        }
    }

    private fun checkPermission(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
        /*int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);*/return result == PackageManager.PERMISSION_GRANTED /*&&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED*/
    }


    private fun requestPermission() {

        //ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            200
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        if (permissions != null) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        when (requestCode) {
            200 -> if (grantResults.size > 0) {
                val locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                /*boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readStorageAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;*/

                //if (locationAccepted && cameraAccepted && readStorageAccepted && writeStorageAccepted)
                if (locationAccepted) if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        showMessageOKCancel(
                            "التطبيق يحتاج بعض الصلاحيات"
                        ) { dialog, which ->
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                //requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},
                                requestPermissions(
                                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                    200
                                )
                            }
                        }
                        return
                    }
                }
            }
        }
    }



    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MainActivity)
            .setMessage(message)
            .setPositiveButton("تم", okListener)
            .setNegativeButton("الغاء", null)
            .create()
            .show()
    }
}