package com.ananananzhuo.fragmentcommunicationsample.activitycommunication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ananananzhuo.activityresultcontractsdemo.databinding.FragmentActivityComm2Binding

class ActivityCommFragment2: Fragment() {
    private val binding : FragmentActivityComm2Binding by lazy {
        FragmentActivityComm2Binding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReturndata.setOnClickListener {
            requireActivity().setResult(Activity.RESULT_OK,Intent().apply {
                putExtra("nums",123)
            })
            requireActivity().finish()
        }
    }
}