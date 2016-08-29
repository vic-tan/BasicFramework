package com.support.galleryfinal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.support.R;
import com.support.galleryfinal.model.PhotoInfo;
import com.support.utils.ImageLoadUtils;
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
        ViewHolder holder;
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
        try {
            if (position == list.size()) {
                ImageLoadUtils.INSTANCE.loadImageView(holder.image, "drawable://" + R.mipmap.common_add_photo);
                if (position == maxSize) {
                    holder.image.setVisibility(View.INVISIBLE);
                }
            } else {
                ImageLoadUtils.INSTANCE.loadImageView(holder.image, "file://" + list.get(position).getPhotoPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(View convertView, ViewHolder holder) {
        holder.image = (ImageView) convertView.findViewById(R.id.iv_photo);
    }

    static class ViewHolder {
        private ImageView image;
    }

    public static boolean isDisplayAddbtn(View view) {
        return view.findViewById(R.id.iv_photo).getVisibility() == View.VISIBLE;
    }
}
