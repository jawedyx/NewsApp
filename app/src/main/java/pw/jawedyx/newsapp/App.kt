package pw.jawedyx.newsapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pw.jawedyx.newsapp.di.koinModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(koinModules)
        }
    }
}