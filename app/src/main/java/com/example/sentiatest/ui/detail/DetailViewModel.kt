package com.example.sentiatest.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.sentiatest.data.Data

class DetailViewModel(data: Data, app: Application): AndroidViewModel(app) {
    private val _data = MutableLiveData<Data>()
    val data: LiveData<Data>
        get() = _data

    init {
        _data.value = data
    }
    class Factory( private val data: Data,
                   private val app: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                return DetailViewModel( data, app) as T
            }
            throw IllegalArgumentException("Unknown View class")
        }

    }
}