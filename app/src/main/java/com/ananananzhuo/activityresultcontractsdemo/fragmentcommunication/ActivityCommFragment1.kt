package com.ananananzhuo.fragmentcommunicationsample.activitycommunication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.ananananzhuo.activityresultcontractsdemo.databinding.FragmentActivityComm1Binding

class ActivityCommFragment1: Fragment() {
    private val launcher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            binding.tvSend.text = result.data?.getIntExtra("nums",-1).toString()
        }
    }
    private val binding : FragmentActivityComm1Binding by lazy {
        FragmentActivityComm1Binding.inflate(layoutInflater)
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
        binding.btnSend.setOnClickListener {
            launcher.launch(Intent(requireContext(),Comm2Activity::class.java))
        }
    }
}