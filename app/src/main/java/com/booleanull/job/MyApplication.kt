package com.booleanull.job

import android.app.Application
import com.booleanull.job.di.AppComponent
import com.booleanull.job.di.AppModule
import com.booleanull.job.di.DaggerAppComponent
import com.booleanull.job.di.NetworkModule

class MyApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}