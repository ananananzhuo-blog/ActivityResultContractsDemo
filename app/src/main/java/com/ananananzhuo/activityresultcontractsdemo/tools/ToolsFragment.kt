package com.ananananzhuo.activityresultcontractsdemo.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

/**
 * author  :mayong
 * function:
 * date    :2021/9/28
 **/
class ToolsFragment : Fragment() {
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
        request.launch(callback?.generateIntent())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        request.unregister()
    }
}