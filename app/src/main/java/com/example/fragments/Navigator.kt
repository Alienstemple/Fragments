package com.example.fragments

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun showOpenProcess()
    fun showEnterNameGeneral()
    fun showEnterName()
    fun showChooseImage()
    fun showChange()
    fun goBack()
    fun goToMenu()

    fun setName(name: String)
    fun setImage(img: Int)
}