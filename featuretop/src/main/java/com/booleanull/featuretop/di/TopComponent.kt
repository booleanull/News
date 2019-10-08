package com.booleanull.featuretop.di

import com.booleanull.featuretop.TopFragment
import dagger.Component

@TopScope
@Component(modules = [TopModule::class])
interface TopComponent {

    fun inject(fragment: TopFragment)
}