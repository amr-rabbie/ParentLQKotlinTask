package srandroiddeveloper.amrrabbie.parenthqtask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(

	@field:SerializedName("all")
	val all: Int
) : Parcelable