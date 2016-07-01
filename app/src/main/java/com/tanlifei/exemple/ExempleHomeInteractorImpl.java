package com.tanlifei.exemple;

import com.tanlifei.framework.main.ui.activity.HomeActivity_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanlifei on 16/5/12.
 */
public class ExempleHomeInteractorImpl implements ExempleHomeInteractor {


    @Override
    public List<ExempleHomeListBean> addList() {
        List<ExempleHomeListBean> list = new ArrayList<>();
        list.add(new ExempleHomeListBean("baseAdater", HomeActivity_.class,
                "Android 万能的Adapter for ListView,RecyclerView,GridView等，支持多种Item类型的情况。简单的数据绑定(ListView与其使用方式一致)只需要简单的将Adapter继承CommonAdapter，复写convert方法即可。省去了自己编写ViewHolder等大量的重复的代码。" +
                "\n可以通过holder.getView(id)拿到任何控件。\nViewHolder中封装了大量的常用的方法，比如holder.setText(id,text)，holder.setOnClickListener(id,listener)等，可以支持使用。"));
        return list;
    }


}
