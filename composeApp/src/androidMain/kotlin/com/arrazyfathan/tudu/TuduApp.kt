package com.arrazyfathan.tudu

import android.app.Application
import com.arrazyfathan.tudu.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext

class TuduApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        initKoin {
            androidContext(this@TuduApp)
        }
    }
}
