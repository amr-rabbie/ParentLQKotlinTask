package srandroiddeveloper.amrrabbie.parenthqtask.dp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "weather_table")
@Parcelize
class Weather(var city:String ) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id=0


    override fun toString(): String {
        return "Weather(city='$city', id=$id)"
    }


}