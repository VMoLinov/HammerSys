package com.testing.hammersys.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testing.hammersys.remote.ApiHolder

class ViewModelFactory(private val application: Application, private val api: ApiHolder) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java, ApiHolder::class.java)
            .newInstance(application, api)
    }
}
