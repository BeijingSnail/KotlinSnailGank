package com.example.kotlin.snail.kotlinsnailgank.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.activity.WebViewActivity
import com.example.kotlin.snail.kotlinsnailgank.adapter.IosAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.bean.DataBean
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import com.example.kotlin.snail.kotlinsnailgank.common.IOS
import com.example.kotlin.snail.kotlinsnailgank.observable.ObservableHelper
import com.example.kotlin.snail.kotlinsnailgank.view.SpaceItemDecoration
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_ios.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class IosFragment : Fragment(), XRecyclerView.LoadingListener, Observer<List<DataBean>> {

    private var page = 0
    var adapter: IosAdapter<DataBean>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_ios, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initXRecyclerView()
    }

    private fun initXRecyclerView() {
        val layoutManger = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        ios_xrv.layoutManager = layoutManger
        //设置刷新风格
        ios_xrv.setRefreshProgressStyle(Constant.REFRESHSTYLE)
        ios_xrv.setLoadingMoreProgressStyle(Constant.LOADINGSTYLE)
        adapter = IosAdapter<DataBean>(context).apply {
            setRecyclerViewItemClickListener(object : BaseAdapter.RecyclerViewItemClickListener {
                override fun onItemclick(view: View, position: Int) {
                    val bean = adapter?.getItem(position) as DataBean
                    startActivity(Intent(context, WebViewActivity::class.java).putExtra(Constant.OPENURL, bean.url))
                }
            })
        }
        ios_xrv.adapter = adapter
        ios_xrv.addItemDecoration(SpaceItemDecoration(10))
        ios_xrv.setLoadingListener(this)
        ios_xrv.refresh()
    }

    override fun onLoadMore() {
        loadData(++page)
    }

    override fun onRefresh() {
        page = 1
        loadData(page)
    }

    private fun loadData(page: Int) {
        ObservableHelper.getDataObservable(IOS,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onNext(t: List<DataBean>?) {
        if (page == 1) {
            adapter?.removewAll()
            adapter?.dataSet = t as ArrayList<DataBean>
        } else {
            adapter?.addData(t as ArrayList<DataBean>)
        }
    }

    override fun onError(e: Throwable?) {
        if (page == 1) {
            ios_xrv.refreshComplete()
        } else {
            ios_xrv.loadMoreComplete()
        }
    }

    override fun onCompleted() {
        if (page == 1) {
            ios_xrv.refreshComplete()
        } else {
            ios_xrv.loadMoreComplete()
        }
    }

}
