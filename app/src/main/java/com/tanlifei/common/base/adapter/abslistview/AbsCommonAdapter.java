package com.tanlifei.common.base.adapter.abslistview;

import android.content.Context;
import android.view.LayoutInflater;

import com.tanlifei.common.base.adapter.ItemViewDelegate;
import com.tanlifei.common.base.adapter.recycler.RvViewHolder;

import java.util.List;

/**
 * Created by zhy on 16/4/9.
 */
public abstract class AbsCommonAdapter<T> extends AbsMultiItemTypeAdapter<T>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public AbsCommonAdapter(final Context context, final int layoutId, List<T> datas)
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
            public void convert(RvViewHolder holder, T t, int position) {

            }

            @Override
            public void convert(AbsViewHolder holder, T t, int position) {
                AbsCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(AbsViewHolder holder, T t, int position);


}
