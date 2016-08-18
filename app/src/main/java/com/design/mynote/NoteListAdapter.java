package com.design.mynote;

import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 杰‘z on 2016/8/12.
 */
public class NoteListAdapter extends BaseAdapter<Note, NoteListAdapter.ViewHolder> {

    public NoteListAdapter(BaseActivity context, List<Note> list) {
        super(context, list, R.layout.item_note);
    }



    @Override
    public void findView(View view, NoteListAdapter.ViewHolder viewHolder) {
        viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
    }

    @Override
    public void setItemView(NoteListAdapter.ViewHolder viewHolder, Note item, int position) {
        viewHolder.tv_title.setText(item.note);
    }

    @Override
    public NoteListAdapter.ViewHolder getViewHolder() {
        return new ViewHolder();
    }

    public class ViewHolder {
        public TextView tv_title;
    }
}
