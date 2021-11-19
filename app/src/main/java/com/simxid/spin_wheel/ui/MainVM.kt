package com.simxid.spin_wheel.ui

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.simxid.spin_wheel.data.api.ApiWheelRequest
import com.simxid.spin_wheel.data.models.ResponseWheel

class MainVM:BaseObservable() {
    @Bindable var load = MutableLiveData<Boolean>()
    @Bindable var error = ObservableField<String>()
    @Bindable var wheel = MutableLiveData<ResponseWheel.Wheel>()
    @Bindable var msg = MutableLiveData<String>()

    fun fetchWheel(){
        ApiWheelRequest.randomWheel(100,0, object :ApiWheelRequest.OnWheelListener {
            override fun onSuccess(data: ResponseWheel.Wheel?) {
                wheel.postValue(data)
                msg.postValue("SPEED UPDATED")
            }

            override fun onLoad(state: Boolean) {
                load.postValue(state)
                if (state) msg.postValue("FETCHING RPM")
            }

            override fun onFailed(errorMsg: String?) {
                error.set(errorMsg)
                load.postValue(false)
                msg.postValue("UNABLE TO UPDATE SPEED")
            }
        })
    }
}