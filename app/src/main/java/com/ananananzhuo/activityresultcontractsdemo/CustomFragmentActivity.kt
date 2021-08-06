package com.ananananzhuo.activityresultcontractsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CustomFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_fragment)
        supportFragmentManager.beginTransaction().add(R.id.custom_container, CustomFragment())
            .commitNowAllowingStateLoss()
    }
}