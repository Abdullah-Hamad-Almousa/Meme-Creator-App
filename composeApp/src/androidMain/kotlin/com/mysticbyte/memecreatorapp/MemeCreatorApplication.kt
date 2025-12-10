package com.mysticbyte.memecreatorapp

import android.app.Application
import com.mysticbyte.memecreatorapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MemeCreatorApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MemeCreatorApplication)
        }
    }

}