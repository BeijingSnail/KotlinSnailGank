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
import com.example.kotlin.snail.kotlinsnailgank.adapter.AndroidAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseFragment
import com.example.kotlin.snail.kotlinsnailgank.bean.AndroidBean
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import com.example.kotlin.snail.kotlinsnailgank.view.SpaceItemDecoration
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.activity_android_fragment.*

class AndroidFragment : BaseFragment(), XRecyclerView.LoadingListener {
    var page = 0

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
        var adapter = AndroidAdapter<AndroidBean>(context)
        android_xrv.adapter = adapter
        android_xrv.addItemDecoration(SpaceItemDecoration(10))
        android_xrv.setLoadingListener(this)
        android_xrv.refresh()
        TODO("点击事件")
    }

    override fun onLoadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        page = 1
        loadData(page)
    }

    private fun loadData(page: Int) {

    }

}
