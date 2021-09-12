package com.example.kotlinmvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvm.databinding.ActivityMainBinding

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
        mainViewModel.getBannerList().observe(this) {
            binding.tvContent.text = it.getOrNull()?.data?.toString()
        }
    }
}