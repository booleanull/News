package com.booleanull.news

import androidx.fragment.app.Fragment
import com.booleanull.featurefavorite.FavoriteFragment
import com.booleanull.featurenews.NewsFragment
import com.booleanull.featuretop.TopFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Screens {

    class TopScreen: SupportAppScreen() {

        override fun getFragment(): Fragment {
            return TopFragment()
        }
    }

    class NewsScreen: SupportAppScreen() {

        override fun getFragment(): Fragment {
            return NewsFragment()
        }
    }

    class FavoriteScreen: SupportAppScreen() {

        override fun getFragment(): Fragment {
            return FavoriteFragment()
        }
    }
}