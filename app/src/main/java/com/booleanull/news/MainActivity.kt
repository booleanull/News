package com.booleanull.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.booleanull.corenetworkimpl.NetworkRepository
import com.booleanull.news.di.DaggerTopComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerTopComponent.builder()
            .appComponent(AppInjectHelper.provideAppComponent(applicationContext))
            .build()
            .inject(this)

        GlobalScope.launch {
            Log.d("TESTQ", networkRepository.getTopHeadlines("ru", 1).await().toString())
        }
    }
}
