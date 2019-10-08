package com.booleanull.featuretop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.booleanull.corenetworkimpl.NetworkRepository
import com.booleanull.featuretop.di.DaggerTopComponent
import com.booleanull.featuretop.di.TopModule
import kotlinx.android.synthetic.main.fragment_top.*
import kotlinx.coroutines.*
import javax.inject.Inject

class TopFragment : Fragment() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerTopComponent
            .builder()
            .topModule(TopModule())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            tv_info.text = withContext(Dispatchers.IO) {
                networkRepository.getTopHeadlines("ru", 1).await().toString()
            }
        }
    }
}