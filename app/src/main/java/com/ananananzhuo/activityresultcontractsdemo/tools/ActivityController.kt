package com.ananananzhuo.activityresultcontractsdemo.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * author  :mayong
 * function:
 * date    :2021/9/28
 **/
class ActivityController(val activity: AppCompatActivity) {

    companion object {
        fun getInstance(activity: AppCompatActivity) = ActivityController(activity)
    }

    fun startActivity(lifeInterface: LifeInterface) {
        val fragment = ToolsFragment()
        fragment.setCallback(lifeInterface)
        activity.supportFragmentManager.beginTransaction()
            .add(fragment, "${System.currentTimeMillis()}").commit()

    }

}