package com.example.kotlin.snail.kotlinsnailgank.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kotlin.snail.kotlinsnailgank.R


/**
 * A simple [Fragment] subclass.
 */
class ResFragment : Fragment() {
    private val TYPE = "拓展资源"
    private val COUNT = 10
    private var page = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_res, container, false)
    }

}// Required empty public constructor
