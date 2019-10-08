package com.booleanull.featuretop.di

import com.booleanull.corenetworkimpl.NetworkRepository
import dagger.Module
import dagger.Provides

@Module
class TopModule {

    @TopScope
    @Provides
    internal fun provideNetworkRepository() = NetworkRepository()
}