package com.sophia.search.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sophia.search.Room.AppRepository
import java.lang.IllegalArgumentException

class AppViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val repository = AppRepository(application)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("UnKown class name.")
    }
}