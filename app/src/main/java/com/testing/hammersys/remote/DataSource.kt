package com.testing.hammersys.remote

import com.testing.hammersys.BuildConfig
import com.testing.hammersys.model.CategoriesDTO
import com.testing.hammersys.model.FilmsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataSource {

    @GET("discover/movie?api_key=$TOKEN&page=")
    fun loadData(@Query("page") page: Int): Call<FilmsDTO>

    @GET("genre/movie/list?api_key=$TOKEN")
    fun loadGenres(): Call<CategoriesDTO>

    companion object {
        const val TOKEN = BuildConfig.FILMS_API_KEY
    }
}
