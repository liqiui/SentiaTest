package com.example.sentiatest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sentiatest.data.Result
import com.example.sentiatest.data.sampleData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class HomeViewModel : ViewModel() {

    // The internal MutableLiveData Data that stores the most recent data
    private val _result = MutableLiveData<Result>()

    // The external immutable LiveData for the response Data
    val result: LiveData<Result>
        get() = _result

    init {
        getDataFromSample()
    }

    private fun getDataFromSample() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(Result::class.java)
        val sampleData = jsonAdapter.fromJson(sampleData)
        _result.value = sampleData ?: Result(emptyList())
    }
}