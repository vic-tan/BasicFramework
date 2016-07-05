package com.tanlifei.exemple.baseadapter;

import android.app.ListActivity;
import android.os.Bundle;

import com.tanlifei.exemple.baseadapter.adapter.ExempleBaseAdpterChatAdapter;
import com.tanlifei.exemple.baseadapter.bean.ExempleBaseAdpterChatMessage;
import com.tanlifei.framework.R;

import java.util.ArrayList;


/**
 * Created by tanlifei on 15/9/4.
 */
public class ExempleBaseAdpterMutliItemTypeActivity extends ListActivity
{
    private ArrayList<ExempleBaseAdpterChatMessage> mDatas = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDatas();
        getListView().setDivider(null);

        setListAdapter(new ExempleBaseAdpterChatAdapter(this,mDatas));
    }


    private void initDatas()
    {

        ExempleBaseAdpterChatMessage msg = null;
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);

        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_renma, "renma", "where are you ",
                null, true);
        mDatas.add(msg);
        msg = new ExempleBaseAdpterChatMessage(R.mipmap.exemple_base_adapter_xiaohei, "xiaohei", "where are you ",
                null, false);
        mDatas.add(msg);

    }
}
