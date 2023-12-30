package com.bataklik.brewhome

import android.app.Application
import com.bataklik.brewhome.data.BeerContainer
import com.bataklik.brewhome.data.DefaultBeerContainer

/**
 * De applicatieklasse.
 * @property container De container voor het ophalen van de repository.
 * @see BeerContainer
 */
class BeerApplication : Application() {
    lateinit var container: BeerContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultBeerContainer(applicationContext)
    }
}