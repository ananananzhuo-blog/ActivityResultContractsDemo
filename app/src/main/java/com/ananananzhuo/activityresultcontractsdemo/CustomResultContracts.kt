package com.ananananzhuo.activityresultcontractsdemo

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

/**
 * author  :mayong
 * function:自定义ActivityResultContract
 * date    :2021/7/31
 **/
class CustomResultContracts : ActivityResultContract<Int, String>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, DestinishActivity::class.java).putExtra("input",input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return intent?.getStringExtra("data") ?: "未返回数据"
    }
}