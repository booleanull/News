package com.booleanull.core.di

import android.content.Context
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideContext(): Context

    fun provideCicerone(): Cicerone<Router>
}

