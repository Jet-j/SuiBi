package com.design.mynote;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 杰‘z on 2016/8/13.
 */
public class GroupAdapter extends BaseAdapter<PopItem, GroupAdapter.ViewHolder> {
    public GroupAdapter(BaseActivity context, List list) {
        super(context, list, R.layout.pop_item);
    }

    @Override
    public void findView(View view, GroupAdapter.ViewHolder viewHolder) {
        viewHolder.item_tv = (TextView) view.findViewById(R.id.pop_item_tv);
        viewHolder.item_icon = (ImageView) view.findViewById(R.id.pop_item_icon);
    }


    @Override
    public void setItemView(GroupAdapter.ViewHolder viewHolder, PopItem item, int position) {
        viewHolder.item_tv.setText(item.itemText);
        viewHolder.item_icon.setImageResource(item.itemIcon);
    }

    @Override
    public GroupAdapter.ViewHolder getViewHolder() {
        return new ViewHolder();
    }

    public class ViewHolder {
        public TextView item_tv;
        public ImageView item_icon;
    }
}
