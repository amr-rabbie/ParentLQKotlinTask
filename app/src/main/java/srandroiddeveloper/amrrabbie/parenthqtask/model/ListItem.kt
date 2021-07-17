package srandroiddeveloper.amrrabbie.parenthqtask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(

	@field:SerializedName("dt")
	val dt: Int,

	@field:SerializedName("pop")
	val pop: Int,

	@field:SerializedName("visibility")
	val visibility: Int,

	@field:SerializedName("dt_txt")
	val dtTxt: String,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>,

	@field:SerializedName("main")
	val main: Main,

	@field:SerializedName("clouds")
	val clouds: Clouds,

	@field:SerializedName("sys")
	val sys: Sys,

	@field:SerializedName("wind")
	val wind: Wind
) : Parcelable