package com.arrazyfathan.tudu

import android.app.Application
import com.arrazyfathan.tudu.di.initKoin
import org.koin.android.ext.koin.androidContext

class TuduApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@TuduApp)
        }
    }
}
