package srandroiddeveloper.amrrabbie.parenthqtask.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import srandroiddeveloper.amrrabbie.parenthqtask.databinding.WeatherItemBinding
import srandroiddeveloper.amrrabbie.parenthqtask.model.ListItem

class WeatherAdapter(val context: Context,val list:List<ListItem>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() { override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.WeatherViewHolder {
        return WeatherViewHolder(WeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        var item:ListItem=list.get(position)

        holder.binding.apply {
            date.text=item.dtTxt
            description.text=item.weather[0].description
            temp.text=item.main.temp.toString()
            humidity.text=item.main.humidity.toString()
            pressure.text=item.main.pressure.toString()
            windspeed.text=item.wind.speed.toString()
            winddegree.text=item.wind.deg.toString()
            clouds.text=item.clouds.all.toString()
            visibility.text=item.visibility.toString()

            img.load("http://openweathermap.org/img/w/" +item.weather[0].icon + ".png"){
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener {mview->
            Toast.makeText(context,"Temp: ${item.main.temp} \n Max temp: ${item.main.tempMax} \n Min temp: ${item.main.tempMin}",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount()=list.size

    inner class WeatherViewHolder(val binding: WeatherItemBinding):
            RecyclerView.ViewHolder(binding.root)
}