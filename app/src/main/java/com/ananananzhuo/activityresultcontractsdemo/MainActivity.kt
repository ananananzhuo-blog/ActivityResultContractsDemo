package com.ananananzhuo.activityresultcontractsdemo

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.ananananzhuo.fragmentcommunicationsample.activitycommunication.Comm1Activity
import com.ananananzhuo.mvvm.activity.CustomAdapterActivity
import com.ananananzhuo.mvvm.bean.bean.ItemData
import com.ananananzhuo.mvvm.callback.CallData
import com.ananananzhuo.mvvm.callback.Callback
import com.ananananzhuo.mvvm.callback.ImageCallback
import com.ananananzhuo.mvvm.utils.logEE
import java.io.File

class MainActivity : CustomAdapterActivity() {
    override fun showFirstItem(): Boolean {
        return true
    }

    private var datas = mutableListOf(
        ItemData(title = "跳转拍照页面1", callback = object : Callback {
            override fun callback(callData: CallData) {
                takePicture.launch(null)
            }
        }),
        ItemData(title = "StartActivityResult2", callback = object : Callback {
            override fun callback(callData: CallData) {
                startForResult.launch(Intent(this@MainActivity, DestinishActivity::class.java))
            }
        }),
        ItemData(title = "请求相机权限3", callback = object : Callback {
            override fun callback(callData: CallData) {
                cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }),
        ItemData(title = "请求多个权限，相机和存储4", callback = object : Callback {
            override fun callback(callData: CallData) {
                mutlePermission.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
            }
        }),
        ItemData(title = "跳转拍照页面，返回一个小的预览图5", callback = object : Callback {
            override fun callback(callData: CallData) {
//               takePreviewPic.launch(Uri.fr)
                val file = File(getExternalFilesDir(""), "pic")
                if (!file.exists()) {
                    file.mkdirs()
                }
                val picFile = File(file, "${System.currentTimeMillis()}.jpg")
                takePreviewPic.launch(
                    FileProvider.getUriForFile(
                        this@MainActivity,
                        "$packageName.provider",
                        picFile
                    )
                )
            }
        }),
        ItemData(title = "TakeVideo拍视频,已经deprecated不讨论6", callback = object : Callback {
            override fun callback(callData: CallData) {

            }
        }),
        ItemData(title = "CaptureVideo拍视频7", callback = object : Callback {
            override fun callback(callData: CallData) {
                val file = File(getExternalFilesDir(""), "video")
                if (!file.exists()) {
                    file.mkdirs()
                }
                val videoFile = File(file, "${System.currentTimeMillis()}.mp4")
                captureVideo.launch(
                    FileProvider.getUriForFile(
                        this@MainActivity,
                        "$packageName.provider",
                        videoFile
                    )
                )
            }

        }),
        ItemData(title = "PickContact获取联系人8", callback = object : Callback {
            override fun callback(callData: CallData) {
                pickContact.launch(null)
            }
        }),
        ItemData(title = "GetContent调用文件选择器9", callback = object : Callback {
            override fun callback(callData: CallData) {
                getContent.launch("image/*")
            }
        }),
        ItemData(title = "GetMultipleContents可以返回多个内容，用法和GetContent一样，10"),
        ItemData(title = "OpenDocument打开文档11", callback = object : Callback {
            override fun callback(callData: CallData) {
                openDocument.launch(arrayOf("image/*"))
            }

        }),
        ItemData(title = "OpenDocumentTree暂时未写12"),
        ItemData(
            title = "CreateDocument会发生崩溃13",
            callback = object : Callback {
                override fun callback(callData: CallData) {
                    createDocument.launch("")
                }
            },
            content = "我的手机无法使用这个功能No Activity found to handle Intent { act=android.intent.action.CREATE_DOCUMENT typ=*/* (has extras) "
        ),
        ItemData(title = "自定义ActivityResultContract14",callback =object :Callback{
            override fun callback(callData: CallData) {
                customContract.launch(1)
            }
        }),
        ItemData(title = "跳转到新页面，测试fragment中使用ActivityRC",callback = object :Callback{
            override fun callback(callData: CallData) {
                startActivity(Intent(this@MainActivity,CustomFragmentActivity::class.java))
            }
        }),
        ItemData(title = "借助fragment写一个万能的可以在任意位置启动startActivityForResult工具"){
            startActivity(Intent(this,StartToolsActivity::class.java))
        },
        ItemData(title = "两个activity中的两个fragment实现数据的传输"){
            startActivity(Intent(this,Comm1Activity::class.java))
        }
    )

    /**
     * 自定义ActivityResultContract
     */
    private val customContract = registerForActivityResult(CustomResultContracts()){
        getData(14).content="自定义Contracts返回数据：$it"
        getData(14).notifyDataSetChange()
    }


    private val createDocument =
        registerForActivityResult(ActivityResultContracts.CreateDocument()) {

        }

    private val openDocument = registerForActivityResult(ActivityResultContracts.OpenDocument()) {

    }

    /**
     * 调用文件选择器，获取想要类型的文件
     */
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        logEE(it.toString())
    }

    /**
     * 获取联系人
     */
    private val pickContact = registerForActivityResult(ActivityResultContracts.PickContact()) {
        logEE(it.toString())
        getData(8).apply {
            content = it.toString()
            notifyDataSetChange()
        }
    }

    /**
     * 拍摄视频的方法
     */
    private val captureVideo = registerForActivityResult(ActivityResultContracts.CaptureVideo()) {
        logEE("拍摄视频成功:$it")
    }

    /**
     * 已过时方法
     */
    private val takeVideo = registerForActivityResult(ActivityResultContracts.TakeVideo()) {

    }

    /**
     * 拍照保存到特定的目录位置，传入的参数应该是一个uri
     */
    private val takePreviewPic = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        logEE("搞预览图片成功")
    }

    override fun getAdapterDatas(): MutableList<ItemData> {
        return datas
    }

    /**
     * 一次请求多个权限
     */
    private val mutlePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            var result = ""
            it.forEach { gaint ->
                result += "获取${gaint.key} 权限 ${if (gaint.value) "成功" else "失败"}"
            }
            getData(4).content = result
            getData(4).notifyDataSetChange()
        }

    /**
     * 请求相机权限
     */
    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            getData(3).apply {
                content = "请求相机权限结果$it"
                notifyDataSetChange()
            }
        }

    private fun getData(i: Int): ItemData {
        return datas[i]
    }

    /**
     * 跳转拍照界面,拍照并保存到共享图库
     */
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            getData(1).let {
                it.notifyDataSetChange()
            }
        }

    /**
     * 展示startActivityForResult的结果
     */
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 3) {
                showResult(it.data?.getStringExtra("data"))
            }
        }


    private fun showResult(data: String?) {
        data?.let {
            datas[2].content = it
            datas[2].notifyDataSetChange()
        }
    }


}