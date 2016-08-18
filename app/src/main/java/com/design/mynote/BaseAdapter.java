package com.design.mynote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 杰‘z on 2016/8/12.
 */
public abstract class BaseAdapter<T, V> extends android.widget.BaseAdapter {
    private BaseActivity context;
    private List<T> list;
    private int layout;

    public BaseAdapter(BaseActivity context, List<T> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    public void add(T t) {
        this.list.add(0, t);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(T t) {
        if (list.contains(t)) {
            list.remove(t);
        }
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        V v = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            try {
                v = getViewHolder();
            } catch (Exception e) {
                e.printStackTrace();
            }
            findView(convertView, v);
            convertView.setTag(v);
        } else {
            v = (V) convertView.getTag();
        }
        setItemView(v, list.get(position), position);
        return convertView;
    }


    public abstract void findView(View view, V viewHolder);


    public abstract void setItemView(V viewHolder, T item, int position);


    public abstract V getViewHolder();
}
