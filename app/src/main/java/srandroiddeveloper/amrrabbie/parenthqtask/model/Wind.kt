package srandroiddeveloper.amrrabbie.parenthqtask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(

	@field:SerializedName("deg")
	val deg: Int,

	@field:SerializedName("speed")
	val speed: Double,

	@field:SerializedName("gust")
	val gust: Double
) : Parcelable