package com.testing.hammersys.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesDTO(
    val genres: List<Category>,
) : Parcelable
