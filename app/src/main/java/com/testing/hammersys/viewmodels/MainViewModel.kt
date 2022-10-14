package com.testing.hammersys.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.testing.hammersys.model.CategoriesDTO
import com.testing.hammersys.model.Category
import com.testing.hammersys.model.Film
import com.testing.hammersys.model.FilmsDTO
import com.testing.hammersys.remote.ApiHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application, api: ApiHolder) :
    AndroidViewModel(application) {
    val films = MutableLiveData<List<Film>>()
    val categories = MutableLiveData<List<Category>>()
    val selectedGenre = 0

    private val dataCallback = object : Callback<FilmsDTO> {

        override fun onResponse(call: Call<FilmsDTO>, response: Response<FilmsDTO>) {
            val serverResponse: FilmsDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                films.value = serverResponse.results
            } else {
                Toast.makeText(getApplication(), "empty data", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "empty data " + response.code())
            }
        }

        override fun onFailure(call: Call<FilmsDTO>, t: Throwable) {
            Toast.makeText(getApplication(), "error data", Toast.LENGTH_SHORT).show()
            Log.d("TAG", "error data " + t.stackTraceToString())
        }
    }

    private val genresCallback = object : Callback<CategoriesDTO> {

        override fun onResponse(call: Call<CategoriesDTO>, response: Response<CategoriesDTO>) {
            val serverResponse: List<Category>? = response.body()?.genres
            if (response.isSuccessful && serverResponse != null) {
                serverResponse.let { categories.value = it }
            } else {
                Toast.makeText(getApplication(), "empty genres", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "empty genres " + response.code())
            }
        }

        override fun onFailure(call: Call<CategoriesDTO>, t: Throwable) {
            Toast.makeText(getApplication(), "error genres", Toast.LENGTH_SHORT).show()
            Log.d("TAG", "error genres " + t.stackTraceToString())
        }
    }

    init {
        api.getData(dataCallback, 1)
        api.getGenres(genresCallback)
    }
}