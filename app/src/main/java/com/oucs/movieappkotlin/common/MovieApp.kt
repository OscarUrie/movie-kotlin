package com.oucs.movieappkotlin.common

import android.app.Application

//Se debe editar el AndroidManifest
class MovieApp:Application() {
    companion object{
        lateinit var instance:MovieApp
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}