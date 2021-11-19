package com.simxid.spin_wheel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.simxid.spin_wheel.R
import com.simxid.spin_wheel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var vm:MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        vm = MainVM()
        binding.lifecycleOwner = this
        binding.mainVm = vm
        binding.btnUpdateSpeed.setOnClickListener { vm.fetchWheel() }
        vm.wheel.observe(this, { data ->
            run {
                binding.tvSpeed.text = data?.random.toString()

            }
        })
        vm.load.observe(this, {
            binding.btnUpdateSpeed.isClickable = !it
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        vm.msg.observe(this, {
            val snack = Snackbar.make(binding.rootView, it, Snackbar.LENGTH_SHORT)
            if (snack.isShown) snack.dismiss() else snack.show()
        })

    }
}