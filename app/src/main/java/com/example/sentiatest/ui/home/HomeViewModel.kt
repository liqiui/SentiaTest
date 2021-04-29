package com.example.sentiatest.ui.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sentiatest.data.Data
import com.example.sentiatest.data.Result
import com.example.sentiatest.data.sampleData
import com.example.sentiatest.network.Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    // The internal MutableLiveData Data that stores the most recent data
    private val _result = MutableLiveData<Result>()
    private val _selectedData = MutableLiveData<Data?>()

    // The external immutable LiveData for the response Data
    val result: LiveData<Result>
        get() = _result
    val selectedData: LiveData<Data?>
        get() = _selectedData
    init {
//        getDataFromSample()
//        getDataFromSample()
        getDataFromNetworkCoroutine()
    }

    fun displayDataDetails(data: Data) {
        _selectedData.value = data
    }

    fun displayDataDetailComplete() {
        _selectedData.value = null
    }
    //getting data from local data for testing
    private fun getDataFromSample() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(Result::class.java)
        val sampleData = jsonAdapter.fromJson(sampleData)
        _result.value = sampleData ?: Result(emptyList())
    }

    // callback version of getting data
    private fun getDataFromNetwork() {
        Api.retrofitService.getData().enqueue(
            object: Callback<Result> {
                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    _result.value = response.body()
                }

                override fun onFailure(call: Call<Result>, t: Throwable) {
                    _result.value = Result( emptyList())
                }
            }
        )
    }

    // Coroutine version of getting data
    private fun getDataFromNetworkCoroutine() {
        viewModelScope.launch {
            try {
                _result.value = Api.retrofitService.getDataCoroutine()
            } catch (e: Exception) {
                _result.value = Result(emptyList())
            }
        }
    }

}