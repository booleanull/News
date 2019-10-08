package com.booleanull.news

import android.app.Application
import android.content.Context
import com.booleanull.news.di.AppComponent
import com.booleanull.news.di.AppComponentProvider
import com.booleanull.news.di.AppModule
import com.booleanull.news.di.DaggerAppComponent

class MyApplication : Application(), AppComponentProvider {

    lateinit var appComponent: AppComponent

    override fun provideAppComponent(): AppComponent {
        if (!this::appComponent.isInitialized) {
            appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
        }
        return appComponent
    }
}

object AppInjectHelper {
    fun provideAppComponent(applicationContext: Context): AppComponent {
        return if (applicationContext is AppComponentProvider) {
            (applicationContext as AppComponentProvider).provideAppComponent()
        } else {
            throw IllegalStateException("The application context you have passed does not implement AppComponentProvider")
        }
    }
}