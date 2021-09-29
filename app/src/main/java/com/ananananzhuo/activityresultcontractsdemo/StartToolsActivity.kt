package com.ananananzhuo.activityresultcontractsdemo

import android.content.Intent
import androidx.activity.result.ActivityResult
import com.ananananzhuo.activityresultcontractsdemo.tools.ActivityController
import com.ananananzhuo.activityresultcontractsdemo.tools.LifeInterface
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData

class StartToolsActivity : CustomAdapterActivity() {
    override fun getAdapterDatas(): MutableList<ItemData> = mutableListOf(
        ItemData("启动activity"){calldata->
            ActivityController.getInstance(this).startActivity(object :LifeInterface{
                override fun generateIntent(): Intent {
                    return Intent(this@StartToolsActivity, ReturnResultActivity::class.java)
                }

                override fun onActivityResult(result: ActivityResult) {
                    if(result.resultCode==100){
                        val data = result.data?.getStringExtra("data")
                        calldata.itemData.content=data
                        calldata.itemData.notifyDataSetChange()
                    }
                }
            })
        }
    )

    override fun showFirstItem(): Boolean =true
}
