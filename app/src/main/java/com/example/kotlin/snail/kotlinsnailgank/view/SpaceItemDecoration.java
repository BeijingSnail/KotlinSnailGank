package com.example.kotlin.snail.kotlinsnailgank.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kotlin.snail.kotlinsnailgank.GankApp;
import com.example.kotlin.snail.kotlinsnailgank.utils.DensityUtil;


/**
 * 设置RecycleView item 间距
 * Created by Snail on 2017/5/22.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = (int) DensityUtil.dip2px(GankApp.Companion.getInstance(), space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = space;
        outRect.right = 1;
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = space;
        }
    }

}
