package com.example.brewhome

import android.app.Application
import com.example.brewhome.data.BeerContainer
import com.example.brewhome.data.DefaultBeerContainer

class BeerApplication : Application() {
    lateinit var container: BeerContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultBeerContainer(applicationContext)
    }
}