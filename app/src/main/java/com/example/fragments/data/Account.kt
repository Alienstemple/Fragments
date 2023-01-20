package com.example.fragments.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    val name: String,
    val img: Int
) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT = Account(name = "Blank", img = 0)
    }
}
