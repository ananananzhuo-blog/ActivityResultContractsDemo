package com.ananananzhuo.activityresultcontractsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData

class ReturnResultActivity : CustomAdapterActivity() {
    override fun getAdapterDatas(): MutableList<ItemData> = mutableListOf(
        ItemData(title="返回结果"){
            setResult(100, Intent().apply {
                putExtra("data","请求返回的结果")
            })
            finish()
        }
    )
    override fun showFirstItem(): Boolean =false

}