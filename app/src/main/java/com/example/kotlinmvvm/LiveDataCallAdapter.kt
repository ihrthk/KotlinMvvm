package com.example.kotlinmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class LiveDataCallAdapter<T>(private val responseType: Type) :
    CallAdapter<T, LiveData<Result<T?>>> {

    //static final class CallAdapted<ResponseT, ReturnT>
    //Converter<ResponseBody, ResponseT> responseConverter,
    //TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type))
    //callAdapter.responseType()
    override fun adapt(call: Call<T>): LiveData<Result<T?>> {
        //LiveData<Result<BannerList>>
        val liveData = MutableLiveData<Result<T?>>()
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                liveData.postValue(Result.success(response.body()))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                liveData.postValue(Result.failure(t))
            }
        })
        return liveData
    }

    override fun responseType() = responseType
}