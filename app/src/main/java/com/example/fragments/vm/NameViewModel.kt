package com.example.fragments.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = mutableSelectedItem

    fun selectItem(item: String) {
        mutableSelectedItem.value = item
    }
}

