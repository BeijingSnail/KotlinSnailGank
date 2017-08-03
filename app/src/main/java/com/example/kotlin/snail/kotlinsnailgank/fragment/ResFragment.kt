package com.example.kotlin.snail.kotlinsnailgank.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.activity.WebViewActivity
import com.example.kotlin.snail.kotlinsnailgank.adapter.ResAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.bean.DataBean
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import com.example.kotlin.snail.kotlinsnailgank.common.RES
import com.example.kotlin.snail.kotlinsnailgank.observable.ObservableHelper
import com.example.kotlin.snail.kotlinsnailgank.view.SpaceItemDecoration
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_res.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */
class ResFragment : Fragment(), XRecyclerView.LoadingListener, Observer<List<DataBean>> {

    private var page = 0
    private var adapter: ResAdapter<DataBean>? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_res, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initXRecyclerView()
    }

    private fun initXRecyclerView() {
        var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        res_xrv.layoutManager = layoutManager
        //设置刷新风格
        res_xrv.setRefreshProgressStyle(Constant.REFRESHSTYLE)
        res_xrv.setLoadingMoreProgressStyle(Constant.LOADINGSTYLE)
        res_xrv.addItemDecoration(SpaceItemDecoration(10))

        adapter = ResAdapter<DataBean>(context).apply {
            setRecyclerViewItemClickListener(object : BaseAdapter.RecyclerViewItemClickListener {
                override fun onItemclick(view: View, position: Int) {
                    val bean = adapter?.getItem(position) as DataBean
                    startActivity(Intent(context, WebViewActivity::class.java).putExtra(Constant.OPENURL, bean.url))
                }
            })
        }
        res_xrv.adapter = adapter
        res_xrv.setLoadingListener(this)
        res_xrv.refresh()
    }

    override fun onLoadMore() {
        loadData(++page)
    }

    override fun onRefresh() {
        page = 1
        loadData(page)
    }

    private fun loadData(page: Int) {
        ObservableHelper.getDataObservable(RES,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onError(e: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted() {
        if (page == 1) {
            res_xrv.refreshComplete()
        } else {
            res_xrv.loadMoreComplete()
        }
    }

    override fun onNext(t: List<DataBean>?) {
        if (page == 1) {
            adapter?.removewAll()
            adapter?.setDataSet(t as List<DataBean>)
        } else {
            adapter?.addData(t as List<DataBean>)
        }
    }
}
