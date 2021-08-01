关注公众号学习更多知识

![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)





## ActivityResultContract是什么
很简单的一句话，`ActivityResultContract`是用来在大部分场景中对`startActivityForResult`和`onActivityResult`进行替代的官方`api`。

`ActivityResultContract`提供了一种类型安全的获取返回值的方式，比如拍照的api会返回泛型指定的bitmap。这避免了我们手动处理onActivityResult回调导致的各种问题。

当然了我认为`ActivityResultContract`最好的地方就是省心，尤其对于系统预置的集中`ActivityResultContract`，只需要两步模板代码即可实现功能。


## 自定义一个ActivityResultContract

要实现自定义行为需要先自定义一个`ActivityResultContract`类，定义如下：

1. 集成ActivityResultContract类

`ActivityResultContract`类中有两个泛型，第一个泛型是I，第二个泛型是O，I表示输入也就是我们启动activity需要putExtra的内容，O表述输入即onActivityResult返回的数据

`ActivityResultContract`有两个方法

- `createIntent`表示创建启动`activity`的`Intent`，其中方法的第二个参数可用于传给待启动`activity`的参数
- `parseResult`表示对返回数据的解析，方法的返回值就是`registerForActivityResult`中回调的数据
```kotlin
class CustomResultContracts : ActivityResultContract<Int, String>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, DestinishActivity::class.java).putExtra("input",input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return intent?.getStringExtra("data") ?: "未返回数据"
    }
}
```

2. 注册监听


```kotlin
 private val customContract = registerForActivityResult(CustomResultContracts()){
        getData(14).content="自定义Contracts返回数据：$it"
        getData(14).notifyDataSetChange()
    }
```

3. 启动activity


```kotlin
customContract.launch(1)
```



## 官方提供的预置ActivityResultContract

### StartActivityForResult启动activity返回结果
本例调用方法后会启动一个activity，新的activity点击返回数据将数据返回到列表中展示
1.  代码

`注册代码`
```kotlin
//注册结果监听
registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 3) {
                showResult(it.data?.getStringExtra("data"))
            }
        }
```
`启动代码`

```kotlin
//启动activity，参数传intent
startForResult.launch(Intent(this@MainActivity, DestinishActivity::class.java))
```

2.  效果

![](https://files.mdnice.com/user/15648/95688d19-b3ec-4dfe-837d-90e52afacc4a.gif)


### TakePicturePreview跳转拍照页面
跳转到拍照页面，拍摄的照片会被保存到图库中

1. 代码

```kotlin
    registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            getData(1).let {
                it.notifyDataSetChange()
            }
        }
```


模拟器录制不变，暂无效果图



### TakePicture拍摄预览图片

`TakePicture`方法会跳转到系统相机拍摄一张照片，返回bitmap，但是不会把图片保存到图库

1. 代码

```kotlin
private val takePreviewPic = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        logEE("搞预览图片成功")
    }
```


### CaptureVideo拍摄视频
拍摄代码，需要说明的是拍摄视频完成后会需要等待较长时间，等待手机处理完视频的存储

```kotlin
 private val captureVideo = registerForActivityResult(ActivityResultContracts.CaptureVideo()) {
        logEE("拍摄视频成功:$it")
    }
```


模拟器录制不变，暂无效果图
### RequestPermission请求权限

非常简洁的方式实现权限申请

1. 申请权限代码

```kotlin
cameraPermission.launch(Manifest.permission.CAMERA)
```

2. 注册申请权限监听


```kotlin
private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            getData(3).apply {
                content = "请求相机权限结果$it"
                notifyDataSetChange()
            }
        }
```
3. 效果
![](https://files.mdnice.com/user/15648/b1e2cd70-8ef5-481d-a3bd-ea54286b9f38.gif)



### RequestMultiplePermissions请求多个权限

1. 调用申请多个权限代码


```kotlin
mutlePermission.launch(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                )
```


2. 注册申请多个权限的代码

```kotlinkotlin
registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            var result = ""
            it.forEach { gaint ->
                result += "获取${gaint.key} 权限 ${if (gaint.value) "成功" else "失败"}"
            }
            getData(4).content = result
            getData(4).notifyDataSetChange()
        }
```

3. 效果
![](https://files.mdnice.com/user/15648/9097f4f3-c3f9-44b1-8ef7-9033c23c0548.gif)


### PickContact获取联系人

1. 请求打开联系人选择页面

```kotlin
pickContact.launch(null)//参数传空
```

2. 监听获取联系人结果

```kotlin
private val pickContact = registerForActivityResult(ActivityResultContracts.PickContact()) {
        logEE(it.toString())
        getData(8).apply {
            content = it.toString()
            notifyDataSetChange()
        }
    }
```
3. 实现效果
![](https://files.mdnice.com/user/15648/4ce82ffa-fa36-4d09-b424-9f80c5d15a5a.gif)


### GetContent打开文件浏览器
实现使用文件浏览器选择图片功能

1. 打开文件浏览器

```kotlinkotlin
getContent.launch("image/*")
```

2. 处理返回结果

```kotlin
 private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        logEE(it.toString())
    }
```
3. 实现效果
因为模拟器没有图片可选，所以没有内容展示

![](https://files.mdnice.com/user/15648/4e2cd452-100d-413c-9bf7-95facdf8cfbb.gif)












