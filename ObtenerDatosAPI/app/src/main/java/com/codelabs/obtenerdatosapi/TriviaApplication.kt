package com.codelabs.obtenerdatosapi

import android.app.Application
import com.codelabs.obtenerdatosapi.data.AppContainer
import com.codelabs.obtenerdatosapi.data.DefaultAppContainer

class TriviaApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}