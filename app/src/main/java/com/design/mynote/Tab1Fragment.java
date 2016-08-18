package com.design.mynote;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 杰‘z on 2016/8/12.
 */
public class Tab1Fragment extends android.support.v4.app.Fragment {

    private SwipeMenuListView listView;
    private MaterialRefreshLayout materialRefreshLayout;
    private NoteListAdapter adapter;
    private View view;
    private List<Note> list = new ArrayList<>();
    private List<Note> note_list = new ArrayList<>();
    private Handler handler = new Handler();
    private NoteDB db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        initView();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {
        listView = (SwipeMenuListView) view.findViewById(R.id.listview);
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.materialRefreshLayout);
        adapter = new NoteListAdapter((BaseActivity) this.getActivity(), list);
        listView.setAdapter(adapter);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getNoteFolder(true);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("note", ((Note) parent.getItemAtPosition(position)));
                ((BaseActivity) getActivity()).next(NoteContentActivity.class, intent);
            }
        });
        db = NoteDB.getInstance(getContext());
        initSwipeListView();
        creatNote();
        getNoteFolder(false);
    }

    private void initSwipeListView() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                int d = DensityUtils.dip2px(90);
                openItem.setWidth(d);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
//                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(DensityUtils.dip2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);


        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                deleteNoteFolder(list.get(position).id);
                return false;
            }


        });

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefreshing();
                        int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。double转int,小数点直接砍掉
                        if (rand == 0) {
                            ToastUtils.showToast("加载成功"); // 通知加载成功
                        } else {
                            ToastUtils.showToast("加载失败");
                        }
                    }
                }, 3 * 1000);
            }
        });
    }

    private void deleteNoteFolder(final String noteid) {
            HashMap<String, String> params = new HashMap<>();
            params.put("folderid", noteid);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id.equals(noteid)) {
                adapter.remove(i);
                return;
            }
        }

    }

    private void creatNote() {
        Note note1 = new Note("1", "喜欢的书籍");
        db.saveNote(note1);
        note_list.add(note1);
        Note note2 = new Note("2", "favorite movies");
        db.saveNote(note2);
        note_list.add(note2);
        Note note3 = new Note("3", "本周计划");
        db.saveNote(note3);
        note_list.add(note3);
        Note note4 = new Note("4", "英语单词");
        db.saveNote(note4);
        note_list.add(note4);
        Note note5 = new Note("5", "我最喜爱的菜谱");
        db.saveNote(note5);
        note_list.add(note5);
        Note note6 = new Note("6", "第一行代码书摘");
        db.saveNote(note6);
        note_list.add(note6);
        Note note7 = new Note("7", "GoogleIO");
        db.saveNote(note7);
        note_list.add(note7);
        Note note8 = new Note("8", "励志格言");
        db.saveNote(note8);
        note_list.add(note8);
        Note note9 = new Note("9", "工作遗留的问题");
        db.saveNote(note9);
        note_list.add(note9);
        Note note10 = new Note("10", "柠檬汁的做法");
        db.saveNote(note10);
        note_list.add(note10);
        Note note11 = new Note("11", "2016十佳电影");
        db.saveNote(note11);
        note_list.add(note11);
        Note note12 = new Note("12", "周末篮球赛");
        db.saveNote(note12);
        note_list.add(note12);
        Note note13 = new Note("13", "我的互联网方法论书摘");
        db.saveNote(note13);
        note_list.add(note13);
        Note note14 = new Note("14", "健身运动");
        db.saveNote(note14);
        note_list.add(note14);
        Note note15 = new Note("15", "RxJava重要知识点");
        db.saveNote(note15);
        note_list.add(note15);
        Note note16 = new Note("16", "Retrofit详解");
        db.saveNote(note16);
        note_list.add(note16);
        Note note17 = new Note("17", "Git项目");
        db.saveNote(note17);
        note_list.add(note17);
        Note note18 = new Note("18", "如何激发自身的潜能？");
        db.saveNote(note18);
        note_list.add(note18);
        Note note19 = new Note("19", "FBI冷读术");
        db.saveNote(note19);
        note_list.add(note19);
        Note note20 = new Note("20", "Jet的一天");
        db.saveNote(note20);
        note_list.add(note20);
        Note note21 = new Note("21", "贝多芬著名交响乐");
        db.saveNote(note21);
        note_list.add(note21);
        Note note22 = new Note("22", "周五晚9点音乐会");
        db.saveNote(note22);
        note_list.add(note22);
        Note note23 = new Note("23", "一些启发");
        db.saveNote(note23);
        note_list.add(note23);
        Note note24 = new Note("24", "经典幽默笑话");
        db.saveNote(note24);
        note_list.add(note24);
        Note note25 = new Note("25", "Belly家地址");
        db.saveNote(note25);
        note_list.add(note25);
        Note note26 = new Note("26", "从0到1");
        db.saveNote(note26);
        note_list.add(note26);
        Note note27 = new Note("27", "怎么打造一个优秀的团队");
        db.saveNote(note27);
        note_list.add(note27);
        Note note28 = new Note("28", "经典例题");
        db.saveNote(note28);
        note_list.add(note28);
        Note note29 = new Note("29", "时事信息");
        db.saveNote(note29);
        note_list.add(note29);
        Note note30 = new Note("30", "中国经济大泡沫书摘");
        db.saveNote(note30);
        note_list.add(note30);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setAddNoteListener(new MainActivity.OnAddNoteListener() {
            @Override
            public void onAdd(String note) {
                Log.i("tab1", "addnote: note_list.size() " + note_list.size());
                Log.i("tab1", "addnote: list.size() " + list.size());
//                adapter.add(new Note(String.valueOf(list.size()), note));  //有点bug
                Note mNote = new Note(String.valueOf(note_list.size() + 1), note);
                db.saveNote(mNote);
                note_list.add(0, mNote);
                Log.i("tab1", "addnote : " + note);
                Log.i("tab1", "addnote content : " + mNote.getContent());
                Log.i("tab1", "addnote time : " + mNote.getLastTime());
                Log.i("tab1", "addnote: note_list.size() " + note_list.size());
                getNoteFolder(true);
                Log.i("tab1", "addnote: list.size() " + list.size());
            }
        });
    }

    private void getNoteFolder(final boolean clean) {
        if (clean) {
            list.clear();
        }
        adapter.addAll(note_list);
//        materialRefreshLayout.finishRefreshing();
    }

}
