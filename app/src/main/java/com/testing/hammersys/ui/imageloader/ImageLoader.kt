package com.testing.hammersys.ui.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadFilmPoster(url: String?, imageView: ImageView)
    fun loadFilmBackDrop(url: String?, imageView: ImageView)
}
