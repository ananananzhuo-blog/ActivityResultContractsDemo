package com.ananananzhuo.activityresultcontractsdemo.tools

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.ananananzhuo.mvvm.utils.logEE

/**
 * author  :mayong
 * function:
 * date    :2021/9/28
 **/
class ToolsFragment : Fragment() {
    private var requestCode: Int? = null
    private var callback: LifeInterface? = null

    private val request = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        callback?.onActivityResult(it)
    }

    fun setCallback(lifeInterface: LifeInterface) {
        this.callback = lifeInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val options = ActivityOptionsCompat.
        request.launch(callback?.start())
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}