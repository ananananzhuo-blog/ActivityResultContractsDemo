package com.ananananzhuo.activityresultcontractsdemo.tools

import android.content.Intent
import androidx.activity.result.ActivityResult

/**
 * author  :mayong
 * function:
 * date    :2021/9/28
 **/
interface LifeInterface {
    fun generateIntent():Intent

    fun onActivityResult(result:ActivityResult)
}