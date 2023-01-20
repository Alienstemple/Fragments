package com.example.fragments.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    val name: String
) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT = Account(name = "Blank")
    }
}
