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
import com.testing.hammersys.ui.adapters.CategoryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application, api: ApiHolder) :
    AndroidViewModel(application) {
    val films = MutableLiveData<List<Film>?>()
    val categories = MutableLiveData<List<Category>>()
    val promo = MutableLiveData<List<Film>>()
    val fullFilms = mutableListOf<Film>()
    var selectedGenrePos = -1

    private val dataCallback = object : Callback<FilmsDTO> {

        override fun onResponse(call: Call<FilmsDTO>, response: Response<FilmsDTO>) {
            val serverResponse: FilmsDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                films.value = serverResponse.results
                fullFilms.addAll(serverResponse.results)
                promo.value = serverResponse.results
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

    var categoryClickListener: ((CategoryAdapter.PromoViewHolder) -> Unit)? = null

    init {
        api.getData(dataCallback, 1)
        api.getGenres(genresCallback)
        categoryClickListener = { handleFilter(it) }
    }

    private fun handleFilter(holder: CategoryAdapter.PromoViewHolder) {
        if (selectedGenrePos != -1) {
            categories.value?.let {
                it[selectedGenrePos].isChecked = false
            }
        }
        categories.value?.let { it[holder.adapterPosition].isChecked = true }
        selectedGenrePos = holder.adapterPosition
        categories.value = categories.value
        val category = categories.value?.get(selectedGenrePos)?.id
        var newFilms: List<Film>? = listOf()
        category?.let {
            newFilms = fullFilms.filter { film -> film.genre_ids.contains(it) }
        }
        films.value = newFilms
    }

    fun restoreData() {
        films.postValue(fullFilms)
    }
}
