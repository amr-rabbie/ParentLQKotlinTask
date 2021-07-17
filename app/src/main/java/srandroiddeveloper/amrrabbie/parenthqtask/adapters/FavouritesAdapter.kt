package srandroiddeveloper.amrrabbie.parenthqtask.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import srandroiddeveloper.amrrabbie.parenthqtask.WeatherActivity
import srandroiddeveloper.amrrabbie.parenthqtask.databinding.LocationsItemBinding
import srandroiddeveloper.amrrabbie.parenthqtask.dp.Weather
import srandroiddeveloper.amrrabbie.parenthqtask.viewmodel.WeatherViewModel

class FavouritesAdapter(val context: Context,val list:List<Weather>,val viewModel: WeatherViewModel) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() { override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesAdapter.FavouritesViewHolder {
        return FavouritesViewHolder(LocationsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavouritesAdapter.FavouritesViewHolder, position: Int) {
        var item:Weather=list.get(position)

        holder.binding.apply {
            txtname.text=item.city

            ibtndelete.setOnClickListener {mview->
                var name:String=item.city
                deleteItem(name)
            }
        }

        holder.itemView.setOnClickListener {mview->
            var name:String=item.city

            var intent: Intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra("State","adp")
            intent.putExtra("City",name)
            context.startActivity(intent)
        }


    }



    override fun getItemCount()=list.size

    inner class FavouritesViewHolder(val binding: LocationsItemBinding):
            RecyclerView.ViewHolder(binding.root)

    private fun deleteItem(name: String) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirm delete")
        builder.setMessage("Are you sure you want delete this location?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Yes") { dialog, which ->
            viewModel.deleteWeatherData(name)
            viewModel.deleteDetailsData(name)
            Toast.makeText(context,"Location data deleted sucessfully",Toast.LENGTH_LONG).show()
            notifyDataSetChanged()
        }

        builder.setNegativeButton("No") { dialog, which ->
           dialog.dismiss()
        }

        /*builder.setNeutralButton("Maybe") { dialog, which ->

        }*/
        builder.show()


    }
}