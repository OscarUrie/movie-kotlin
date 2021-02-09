package com.oucs.movieappkotlin.common

import android.app.Application

//Se debe editar el AndroidManifest
class MyApp:Application() {
    companion object{
        lateinit var instance:MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}