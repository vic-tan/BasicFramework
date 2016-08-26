package com.support.galleryfinal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.support.R;
import com.support.galleryfinal.model.PhotoInfo;
import com.support.imageloader.FanImageLoader;
import com.support.utils.InflaterUtils;
import com.support.utils.ListUtils;

import java.util.List;

/**
 * Created by tanlifei on 16/8/26.
 */
public class PhotoChooseListApdater extends BaseAdapter {
    private List<PhotoInfo> list;
    private Context mContext;
    private int maxSize;


    public PhotoChooseListApdater(Context mContext, List<PhotoInfo> list, int maxSize) {
        this.list = list;
        this.mContext = mContext;
        this.maxSize = maxSize;
    }

    @Override
    public int getCount() {
        return ListUtils.isEmpty(list) ? 1 : list.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = InflaterUtils.inflate(mContext, R.layout.gf_adapter_photo_choose_list_item);
            holder = new ViewHolder();
            initView(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        displayData(position, holder);
        return convertView;
    }

    private void displayData(final int position, ViewHolder holder) {
        if (position == list.size()) {
            FanImageLoader.create("drawable://" + R.mipmap.common_add_photo).setAllRes(R.mipmap.common_add_photo).into(holder.image);
            if (position == maxSize) {
                holder.image.setVisibility(View.GONE);
            } else {
                holder.image.setVisibility(View.VISIBLE);
            }
        } else {
            FanImageLoader.create("file://" + list.get(position).getPhotoPath()).setAllRes(R.mipmap.ic_gf_default_photo).into(holder.image);
        }
    }

    private void initView(View convertView, ViewHolder holder) {
        holder.image = (ImageView) convertView.findViewById(R.id.iv_photo);
    }

    static class ViewHolder {
        private ImageView image;
    }
}
