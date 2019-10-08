package com.booleanull.news

import android.app.Application
import com.booleanull.core.di.AppComponent
import com.booleanull.core.di.AppComponentProvider
import com.booleanull.core.di.AppModule
import com.booleanull.core.di.DaggerAppComponent

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