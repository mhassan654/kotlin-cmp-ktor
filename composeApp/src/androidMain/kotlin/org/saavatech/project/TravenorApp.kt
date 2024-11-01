package org.saavatech.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.saavatech.project.di.initKoin

class TravenorApp:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@TravenorApp)
        }
    }
}