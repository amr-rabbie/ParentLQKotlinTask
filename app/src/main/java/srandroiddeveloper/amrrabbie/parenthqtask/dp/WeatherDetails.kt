package srandroiddeveloper.amrrabbie.parenthqtask.dp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "details_table")
@Parcelize
class WeatherDetails(var city:String , var date:String,var desc:String,var temp:String,
                     var mintemp:String,var maxtemp:String,var humdity:String,var pressure:String,
                     var windspeed:String,var winddegree:String,var clouds:String,var visibilty:String) :
    Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id=0

    @Ignore
    @ColumnInfo(name = "icon_url")
    var img:String=""


    override fun toString(): String {
        return "WeatherDetails(city='$city', date='$date', desc='$desc', temp='$temp', mintemp='$mintemp', maxtemp='$maxtemp', humdity='$humdity', pressure='$pressure', windspeed='$windspeed', winddegree='$winddegree', clouds='$clouds', visibilty='$visibilty', id=$id, img='$img')"
    }


}