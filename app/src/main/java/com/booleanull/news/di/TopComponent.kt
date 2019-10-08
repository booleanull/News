package com.booleanull.news.di

import com.booleanull.news.MainActivity
import dagger.Component

@TopScope
@Component(modules = [TopModule::class], dependencies = [AppComponent::class])
interface TopComponent {

    fun inject(mainActivity: MainActivity)
}