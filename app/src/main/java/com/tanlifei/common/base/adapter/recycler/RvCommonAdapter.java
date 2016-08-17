package com.tanlifei.common.base.adapter.recycler;

import android.content.Context;
import android.view.LayoutInflater;

import com.tanlifei.common.base.adapter.ItemViewDelegate;
import com.tanlifei.common.base.adapter.abslistview.AbsViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class RvCommonAdapter<T> extends RvMultiItemTypeAdapter<T>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public RvCommonAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(RvViewHolder holder, T t, int position)
            {
                RvCommonAdapter.this.convert(holder, t, position);
            }

            @Override
            public void convert(AbsViewHolder holder, T t, int position) {

            }

        });
    }

    protected abstract void convert(RvViewHolder holder, T t, int position);


}
