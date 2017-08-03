package com.example.kotlin.snail.kotlinsnailgank.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.activity.WebViewActivity
import com.example.kotlin.snail.kotlinsnailgank.adapter.AndroidAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseFragment
import com.example.kotlin.snail.kotlinsnailgank.bean.DataBean
import com.example.kotlin.snail.kotlinsnailgank.common.ANDROID
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import com.example.kotlin.snail.kotlinsnailgank.observable.ObservableHelper
import com.example.kotlin.snail.kotlinsnailgank.view.SpaceItemDecoration
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.activity_android_fragment.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class AndroidFragment : BaseFragment(), XRecyclerView.LoadingListener, Observer<List<DataBean>> {

    private var page = 0
    private var adapter: AndroidAdapter<DataBean>? = null

    override fun onCreateFragmentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.activity_android_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initXRecyclerView()
    }

    fun initXRecyclerView() {
        val layoutManaget = LinearLayoutManager(context)
        layoutManaget.orientation = LinearLayoutManager.VERTICAL
        android_xrv.layoutManager = layoutManaget
        //设置刷新风格
        android_xrv.setRefreshProgressStyle(Constant.REFRESHSTYLE)
        android_xrv.setLoadingMoreProgressStyle(Constant.LOADINGSTYLE)
        adapter = AndroidAdapter<DataBean>(context).apply {
            setRecyclerViewItemClickListener(object : BaseAdapter.RecyclerViewItemClickListener {
                override fun onItemclick(view: View, position: Int) {
                    val bean = adapter?.getItem(position) as DataBean
                    startActivity(Intent(context, WebViewActivity::class.java).putExtra(Constant.OPENURL, bean.url))
                }
            })
        }
        android_xrv.adapter = adapter
        android_xrv.addItemDecoration(SpaceItemDecoration(10))
        android_xrv.setLoadingListener(this)
        android_xrv.refresh()
    }

    override fun onLoadMore() {
        loadData(++page)
    }

    override fun onRefresh() {
        page = 1
        loadData(page)
    }

    private fun loadData(page: Int) {
        ObservableHelper.getDataObservable(ANDROID, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)

    }

    override fun onNext(list: List<DataBean>?) {
        if (page == 1) {
            adapter?.removewAll()
            adapter?.dataSet = list as ArrayList<DataBean>
        } else {
            adapter?.addData(list as ArrayList<DataBean>)
        }
    }

    override fun onCompleted() {
        if (page == 1) {
            android_xrv.refreshComplete()
        } else {
            android_xrv.loadMoreComplete()
        }
    }

    override fun onError(e: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

