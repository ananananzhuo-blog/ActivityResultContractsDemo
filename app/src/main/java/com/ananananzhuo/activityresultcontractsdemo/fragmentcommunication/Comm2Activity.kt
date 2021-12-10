package com.ananananzhuo.fragmentcommunicationsample.activitycommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.activityresultcontractsdemo.R
import com.ananananzhuo.activityresultcontractsdemo.databinding.ActivityComm1Binding

class Comm2Activity : AppCompatActivity() {
    val binding : ActivityComm1Binding by lazy {
        ActivityComm1Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.fl_container,ActivityCommFragment2()).commitAllowingStateLoss()
    }
}