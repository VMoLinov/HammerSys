package com.testing.hammersys.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
) : Parcelable
