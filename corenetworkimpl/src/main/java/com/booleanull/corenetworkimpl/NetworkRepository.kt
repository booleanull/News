package com.booleanull.corenetworkimpl

import com.booleanull.core.data.News
import com.booleanull.corenetworkapi.NewsApiService
import com.booleanull.corenetworkapi.data.convertToNews
import com.booleanull.corenetworkimpl.di.DaggerNetworkComponent
import com.booleanull.corenetworkimpl.di.NetworkModule
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class NetworkRepository {

    @Inject
    lateinit var newsApiService: NewsApiService

    init {
        DaggerNetworkComponent.builder()
            .networkModule(NetworkModule())
            .build()
            .inject(this)
    }

    fun getTopHeadlines(country: String, page: Int): Deferred<List<News>> {
        return GlobalScope.async {
            newsApiService.getTopHeadlines(country, page).await().convertToNews()
        }
    }

}