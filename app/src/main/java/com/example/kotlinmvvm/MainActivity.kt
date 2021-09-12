package com.example.kotlinmvvm

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvm.databinding.ActivityMainBinding
import java.lang.reflect.ParameterizedType

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        WanApi.get().bannerList1().enqueue(object : Callback<BannerList?> {
//            override fun onResponse(call: Call<BannerList?>, response: Response<BannerList?>) {
//                binding.tvContent.text = response.body()?.toString()
//            }
//
//            override fun onFailure(call: Call<BannerList?>, t: Throwable) {
//            }
//        })
        testGeneric()
        mainViewModel.getBannerList().observe(this) {
            binding.tvContent.text = it.getOrNull()?.data?.toString()
        }
    }

    private fun testGeneric() {
        val method = WanApi::class.java.getMethod("bannerList2")
        //返回值类型
        val genericReturnType = method.genericReturnType
        //返回值原始类型
        val rawType = (genericReturnType as ParameterizedType).rawType
        //获取第一个泛型类型
        val firstType = genericReturnType.actualTypeArguments[0]
        Log.d(TAG, "reflect() called")
    }
}

private const val TAG = "MainActivity"