package com.example.kotlin.snail.kotlinsnailgank.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.base.BaseFragment
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import kotlinx.android.synthetic.main.activity_android_fragment.*

class AndroidFragment : BaseFragment() {

    override fun onCreateFragmentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.activity_android_fragment, container, false)
        initXRecyclerView()
    }

    fun initXRecyclerView() {
        val layoutManaget = LinearLayoutManager(context)
        layoutManaget.orientation = LinearLayoutManager.VERTICAL
        android_xrv.layoutManager = layoutManaget
        //设置刷新风格
        android_xrv.setRefreshProgressStyle(Constant.REFRESHSTYLE)
        android_xrv.setLoadingMoreProgressStyle(Constant.LOADINGSTYLE)
//        var adapter = new
//        android_xrv.adapter=
    }


}
