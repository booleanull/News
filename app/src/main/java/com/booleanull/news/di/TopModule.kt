package com.booleanull.news.di

import com.booleanull.corenetworkimpl.NetworkRepository
import dagger.Module
import dagger.Provides

@Module
class TopModule {

    @Provides
    @TopScope
    fun provideNetworkRepository() = NetworkRepository()
}