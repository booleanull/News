package com.booleanull.news.di

import com.booleanull.core.di.AppComponent
import com.booleanull.news.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}