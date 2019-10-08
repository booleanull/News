package com.booleanull.corenetworkimpl.di

import com.booleanull.corenetworkapi.NewsApiService
import com.booleanull.corenetworkimpl.Constants.BASE_URL
import com.booleanull.corenetworkimpl.Constants.TOKEN
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor {
            val original = it.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apiKey", TOKEN)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            it.proceed(request)
        }
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun getGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun getGsonConvertFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Singleton
    @Provides
    fun getCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory()
    }

    @Singleton
    @Provides
    fun getRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        okHttpClient: OkHttpClient
    ): NewsApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(NewsApiService::class.java)
    }
}