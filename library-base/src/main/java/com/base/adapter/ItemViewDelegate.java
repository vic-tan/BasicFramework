package com.base.adapter;


import com.base.adapter.abslistview.AbsViewHolder;
import com.base.adapter.recycler.RvViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(RvViewHolder holder, T t, int position);

    void convert(AbsViewHolder holder, T t, int position);

}
