package com.tanlifei.exemple.refreshview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fans.loader.FanImageLoader;
import com.tanlifei.common.base.adapter.pagerview.RecyclingPagerAdapter;
import com.tanlifei.exemple.refreshview.bean.BannerBaen;
import com.tanlifei.framework.R;
import com.tanlifei.support.utils.InflaterUtils;

import java.util.List;

/**
 * Desction:
 * Author:pengjianbo
 * Date:16/3/16 上午11:10
 */
public class BannerAdapter extends RecyclingPagerAdapter {
    private Context mContext;
    private List<BannerBaen> mImageList;

    public BannerAdapter(Context context, List<BannerBaen> mImageList) {
        this.mContext = context;
        this.mImageList = mImageList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = InflaterUtils.inflate(mContext, R.layout.common_banner_item);
            holder.imageView = (ImageView) convertView.findViewById(R.id.banner);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int pos = getPosition(position);
        FanImageLoader.create(mImageList.get(pos).getImage()).setAllRes(R.mipmap.ic_launcher).into(holder.imageView);
        holder.imageView.setFocusable(false);
        holder.imageView.setFocusableInTouchMode(false);
        return convertView;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getPosition(int position) {
        return position % mImageList.size();
    }

    public static class ViewHolder {
        private ImageView imageView;
    }
}
