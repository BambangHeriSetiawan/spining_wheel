package com.simxid.spin_wheel.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ResponseWheel(
	val wheel: List<Wheel>? = null
) : Parcelable {
	@Parcelize
	data class Wheel(
		@field:SerializedName("random")
		val random: Int? = null,

		@field:SerializedName("min")
		val min: Int? = null,

		@field:SerializedName("max")
		val max: Int? = null,

		@field:SerializedName("status")
		val status: String? = null
	) : Parcelable
}


