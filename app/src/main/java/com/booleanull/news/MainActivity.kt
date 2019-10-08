package com.booleanull.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.booleanull.core.di.AppInjectHelper
import com.booleanull.news.di.DaggerActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private val navigator = object : SupportAppNavigator(this, R.id.nav_host_fragment) {
        override fun applyCommands(commands: Array<Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerActivityComponent.builder()
            .appComponent(AppInjectHelper.provideAppComponent(applicationContext))
            .build()
            .inject(this)

        navigation.setOnNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_top -> cicerone.router.replaceScreen(Screens.TopScreen())
                R.id.navigation_news -> cicerone.router.replaceScreen(Screens.NewsScreen())
                R.id.navigation_favorite -> cicerone.router.replaceScreen(Screens.FavoriteScreen())
            }
            return@setOnNavigationItemSelectedListener true
        }

        if (savedInstanceState == null) {
            cicerone.router.replaceScreen(Screens.TopScreen())
        }
        //AppInjectHelper.provideAppComponent(applicationContext).inject(this)

        /*DaggerTopComponent.builder()
            .appComponent(AppInjectHelper.provideAppComponent(applicationContext))
            .build()
            .inject(this)

        GlobalScope.launch {
            Log.d("TESTQ", networkRepository.getTopHeadlines("ru", 1).await().toString())
        }*/
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        cicerone.router.exit()
    }
}
