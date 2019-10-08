package com.booleanull.corenetworkimpl.di

import com.booleanull.corenetworkimpl.NetworkRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun inject(networkRepository: NetworkRepository)
}