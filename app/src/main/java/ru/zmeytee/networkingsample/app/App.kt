package ru.zmeytee.networkingsample.app

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp
import ru.zmeytee.networkingsample.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build()
            )
        }
    }
}