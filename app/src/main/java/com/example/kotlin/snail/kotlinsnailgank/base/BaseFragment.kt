package com.example.kotlin.snail.kotlinsnailgank.base


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.kotlin.snail.kotlinsnailgank.R


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {
    var mContentView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mContentView = onCreateFragmentView(inflater, container, savedInstanceState)

        return mContentView
    }

    /**
     * 创建view方法，子类必须重写
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    abstract fun onCreateFragmentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?

    fun showSnackBar(strId: Int) {
        Snackbar.make(view!!, strId, Snackbar.LENGTH_SHORT)
    }

    fun showSnackBar(str: String) {
        Snackbar.make(view!!, str, Snackbar.LENGTH_SHORT)
    }

}
