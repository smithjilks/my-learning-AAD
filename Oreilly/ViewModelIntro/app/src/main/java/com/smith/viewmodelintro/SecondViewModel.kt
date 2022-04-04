package com.smith.viewmodelintro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SecondViewModel(var passedClicks: Int) : ViewModel() {
    private var _clicks = MutableLiveData<Int>()
    val clicks: LiveData<Int> get() = _clicks


    init {
        _clicks.value = passedClicks + 1
    }

}


class SecondViewModelFactory(var passedClicks: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SecondViewModel::class.java)) {
            return  SecondViewModel(passedClicks) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}