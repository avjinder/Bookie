package com.avjinder.sekhon.bookie

import android.app.Application
import timber.log.Timber

class BookieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}