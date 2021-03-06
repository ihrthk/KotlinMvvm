package com.example.kotlinmvvm

data class ApiBody<T>(
    var `data`: List<T>,
    var errorCode: Int,
    var errorMsg: String
)

data class Banner(
    var desc: String,
    var id: Int,
    var imagePath: String,
    var isVisible: Int,
    var order: Int,
    var title: String,
    var type: Int,
    var url: String
)