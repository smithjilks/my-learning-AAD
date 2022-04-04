package com.smith.viewmodelintro

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstFragmentViewModel: ViewModel() {

//    Old way without livedata
//    var message = ""
//    var clicks = 0


//    New way using livedata
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _clicks = MutableLiveData<Int>()
    val clicks: LiveData<Int> get() = _clicks


    //Override methods to get insights into view model lifecycle
    init {
        Log.i("Test", "View Model destroyed")
        _message.value = ""
        _clicks.value = 0

    }
    override fun onCleared() {
        super.onCleared()
        Log.i("Test", "View Model destroyed")
    }

    // public functions to call from fragments
    fun hello(name: String) {
        _message.value = "Hello $name"
        _clicks.value =_clicks.value?.plus(1)
    }

    fun goodbye(name: String) {
        _message.value = "Goodbye $name"
        _clicks.value = clicks.value?.plus(1)
    }


}