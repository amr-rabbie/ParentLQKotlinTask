package srandroiddeveloper.amrrabbie.parenthqtask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys(

	@field:SerializedName("pod")
	val pod: String
) : Parcelable