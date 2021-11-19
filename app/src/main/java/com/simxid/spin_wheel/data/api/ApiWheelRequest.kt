package com.simxid.spin_wheel.data.api

import android.util.Log
import com.simxid.spin_wheel.data.models.ResponseWheel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException

object ApiWheelRequest {
    private var job = SupervisorJob()
    interface OnWheelListener {
        fun onSuccess(data:ResponseWheel.Wheel?)
        fun onLoad(state:Boolean)
        fun onFailed(errorMsg:String?)
    }
    fun randomWheel(max:Int?, min:Int?, listener:ApiWheelRequest.OnWheelListener){
        listener.onLoad(true)
        try {
            CoroutineScope(CoroutineExceptionHandler { _, throwable -> run {
                job = SupervisorJob()
                listener.onLoad(false)
                listener.onFailed(throwable.message)
            }  }).launch {
                ApiWheel.Factory.build().randomWheel(min, max).let { response ->
                    run {
                        if (response.isSuccessful && response.body()?.isNotEmpty()!!) {
                            listener.onSuccess(response.body()?.get(0))
                        }else {
                            listener.onFailed(response.message())
                        }
                        listener.onLoad(false)
                    }
                }
            }
        }catch (e:HttpException){
            listener.onLoad(false)
            listener.onFailed(e.message)
        }
    }
}