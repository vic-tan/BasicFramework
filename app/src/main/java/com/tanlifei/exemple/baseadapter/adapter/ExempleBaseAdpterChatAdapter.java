package com.tanlifei.exemple.baseadapter.adapter;

import android.content.Context;

import com.tanlifei.common.base.adapter.MultiItemCommonAdapter;
import com.tanlifei.common.base.adapter.MultiItemTypeSupport;
import com.tanlifei.common.base.adapter.ViewHolder;
import com.tanlifei.exemple.baseadapter.bean.ExempleBaseAdpterChatMessage;
import com.tanlifei.framework.R;

import java.util.List;


/**
 * Created by tanlifei on 15/9/4.
 */
public class ExempleBaseAdpterChatAdapter extends MultiItemCommonAdapter<ExempleBaseAdpterChatMessage>
{
    public ExempleBaseAdpterChatAdapter(Context context, List<ExempleBaseAdpterChatMessage> datas)
    {
        super(context, datas, new MultiItemTypeSupport<ExempleBaseAdpterChatMessage>()
        {
            @Override
            public int getLayoutId(int position, ExempleBaseAdpterChatMessage msg)
            {
                if (msg.isComMeg())
                {
                    return R.layout.exemple_base_adapter_item_chat_from_msg;
                }
                return R.layout.exemple_base_adapter_item_chat_send_msg;
            }

            @Override
            public int getViewTypeCount()
            {
                return 2;
            }
            @Override
            public int getItemViewType(int postion, ExempleBaseAdpterChatMessage msg)
            {
                if (msg.isComMeg())
                {
                    return ExempleBaseAdpterChatMessage.RECIEVE_MSG;
                }
                return ExempleBaseAdpterChatMessage.SEND_MSG;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, ExempleBaseAdpterChatMessage chatMessage)
    {

        if (holder.getLayoutId() == R.layout.exemple_base_adapter_item_chat_from_msg) {
            holder.setText(R.id.chat_from_content, chatMessage.getContent());
            holder.setText(R.id.chat_from_name, chatMessage.getName());
            holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());

        } else if (holder.getLayoutId() == R.layout.exemple_base_adapter_item_chat_send_msg) {
            holder.setText(R.id.chat_send_content, chatMessage.getContent());
            holder.setText(R.id.chat_send_name, chatMessage.getName());
            holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());

        }
    }


}
