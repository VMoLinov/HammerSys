package com.testing.hammersys.remote

import com.google.gson.GsonBuilder
import com.testing.hammersys.model.CategoriesDTO
import com.testing.hammersys.model.FilmsDTO
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolder {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val api: DataSource by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DataSource::class.java)
    }

    fun getData(callback: Callback<FilmsDTO>, page: Int) {
        api.loadData(page).enqueue(callback)
    }

    fun getGenres(callback: Callback<CategoriesDTO>) {
        api.loadGenres().enqueue(callback)
    }
}
