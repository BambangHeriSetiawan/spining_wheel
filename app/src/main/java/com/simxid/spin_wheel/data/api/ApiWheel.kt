package com.simxid.spin_wheel.data.api

import com.simxid.spin_wheel.data.models.ResponseWheel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiWheel {
    @Headers("Accept: application/json", "Content-type: application/json")
    @GET(ApiCore.WHEEL_ENDPOINT)
    suspend fun randomWheel(
        @Query("min") min:Int?,
        @Query("max") max:Int?
    ): Response<List<ResponseWheel.Wheel>>

    object Factory {
        fun build():ApiWheel {
            return ApiCore.getRetrofitConfig(ApiCore.BASE_URL).create(ApiWheel::class.java)
        }
    }
}