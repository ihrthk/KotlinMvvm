package com.example.kotlinmvvm

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getBannerList() = WanApi.get().bannerList2()
}