package srandroiddeveloper.amrrabbie.parenthqtask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import srandroiddeveloper.amrrabbie.parenthqtask.databinding.WeatherItemBinding
import srandroiddeveloper.amrrabbie.parenthqtask.dp.WeatherDetails
import srandroiddeveloper.amrrabbie.parenthqtask.model.ListItem

class WeatherOfflineAdapter(val context: Context, val list:List<WeatherDetails>) : RecyclerView.Adapter<WeatherOfflineAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherOfflineAdapter.WeatherViewHolder {
    return WeatherViewHolder(WeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
}

    override fun onBindViewHolder(holder: WeatherOfflineAdapter.WeatherViewHolder, position: Int) {
        var item: WeatherDetails =list.get(position)

        holder.binding.apply {
            date.text=item.date
            description.text=item.desc
            temp.text=item.temp
            humidity.text=item.humdity
            pressure.text=item.pressure
            windspeed.text=item.windspeed
            winddegree.text=item.winddegree
            clouds.text=item.clouds
            visibility.text=item.visibilty

            /*img.load("http://openweathermap.org/img/w/" +item.weather[0].icon + ".png"){
                crossfade(true)
                crossfade(1000)
            }*/
        }

        holder.itemView.setOnClickListener {mview->
            Toast.makeText(context,"Temp: ${item.temp} \n Max temp: ${item.maxtemp} \n Min temp: ${item.mintemp}",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount()=list.size

    inner class WeatherViewHolder(val binding: WeatherItemBinding):
        RecyclerView.ViewHolder(binding.root)
}