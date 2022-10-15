package com.testing.hammersys.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmsDTO(
    val page: Int,
    val results: List<Film>
) : Parcelable
