package com.booleanull.corenetworkapi

import com.booleanull.corenetworkapi.data.NewsEntity
import com.booleanull.corenetworkapi.data.ResponseEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("country") country: String, @Query("page") page: Int): Deferred<ResponseEntity>
}