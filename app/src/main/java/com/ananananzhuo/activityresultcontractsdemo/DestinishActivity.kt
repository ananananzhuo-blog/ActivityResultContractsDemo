package com.ananananzhuo.activityresultcontractsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData
import com.ananananzhuo.mvvm.callback.CallData
import com.ananananzhuo.mvvm.callback.Callback

class DestinishActivity : CustomAdapterActivity() {
    override fun getAdapterDatas(): MutableList<ItemData> {
        return mutableListOf(
            ItemData(title = "返回结果",callback = object :Callback{
                override fun callback(callData: CallData) {
                    val intent = Intent()
                    intent.putExtra("data","来过就好，走吧")
                    setResult(3,intent)
                    finish()
                }
            })
        )
    }

    override fun showFirstItem(): Boolean {
        return false
    }

}