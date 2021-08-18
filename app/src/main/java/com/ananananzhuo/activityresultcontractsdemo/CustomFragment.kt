package com.ananananzhuo.activityresultcontractsdemo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.ananananzhuo.mvvm.utils.logEE

/**
 * A simple [Fragment] subclass.
 * Use the [CustomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn).setOnClickListener {
            request.launch(Intent(activity, DestinishActivity::class.java))//启动新activity
        }
    }

    /**
     * 注册结果监听
     */
    val request = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        logEE(it.data?.getStringExtra("data")!!)
    }
}