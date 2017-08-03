package com.example.kotlin.snail.kotlinsnailgank.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.snail.kotlinsnailgank.R
import com.example.kotlin.snail.kotlinsnailgank.activity.PictureActivity
import com.example.kotlin.snail.kotlinsnailgank.adapter.WelfareAdapter
import com.example.kotlin.snail.kotlinsnailgank.base.BaseAdapter
import com.example.kotlin.snail.kotlinsnailgank.bean.DataBean
import com.example.kotlin.snail.kotlinsnailgank.common.Constant
import com.example.kotlin.snail.kotlinsnailgank.common.WELFARE
import com.example.kotlin.snail.kotlinsnailgank.observable.ObservableHelper
import com.example.kotlin.snail.kotlinsnailgank.view.SpaceItemDecoration
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_welfare.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class WelfareFragment : Fragment(), XRecyclerView.LoadingListener, Observer<List<DataBean>> {

    private var page = 0
    var adapter: WelfareAdapter<DataBean>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_welfare, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initXRecyclerView()
    }

    private fun initXRecyclerView() {
        var layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = GridLayoutManager.VERTICAL
        welfare_xrv.layoutManager = layoutManager
        //设置刷新风格
        welfare_xrv.setRefreshProgressStyle(Constant.REFRESHSTYLE)
        welfare_xrv.setLoadingMoreProgressStyle(Constant.LOADINGSTYLE)
        welfare_xrv.addItemDecoration(SpaceItemDecoration(10))
        adapter = WelfareAdapter<DataBean>(context).apply {
            setRecyclerViewItemClickListener(object : BaseAdapter.RecyclerViewItemClickListener {
                override fun onItemclick(view: View, position: Int) {
                    startActivity(Intent(context, PictureActivity::class.java)
                            .putExtra(Constant.POSITION, position)
                            .putStringArrayListExtra(Constant.UELLIST, transform(adapter!!.dataSet))
                    )
                }
            })
        }
        welfare_xrv.adapter = adapter
        welfare_xrv.setLoadingListener(this)
        welfare_xrv.refresh()

    }

    override fun onLoadMore() {
        loadData(++page)
    }

    override fun onRefresh() {
        page = 1
        loadData(page)
    }

    private fun loadData(page: Int) {
        ObservableHelper.getDataObservable(WELFARE,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onError(e: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted() {
        if (page == 1) {
            welfare_xrv.refreshComplete()
        } else {
            welfare_xrv.loadMoreComplete()
        }
    }

    override fun onNext(t: List<DataBean>?) {
        if (page == 1) {
            adapter?.removewAll()
            adapter?.setDataSet(t as List<DataBean>)
            adapter?.notifyDataSetChanged()
        } else {
            adapter?.addData(t as List<DataBean>)
        }
    }

    /**
     * 提取WelfareBean中的图片url
     */
    fun transform(list: List<DataBean>): ArrayList<String> {
        val arrayList = list.mapTo(ArrayList<String>()) { it.url!! }
        //        val arrayList = ArrayList<String>()
//        for (welfareBean in list) {
//            arrayList.add(welfareBean.url!!)
//        }
        return arrayList
    }

}
