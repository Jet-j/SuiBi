package com.design.mynote;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.demos.tencent_qq_ui.CustomerView.AvatarImageView;
import com.demos.tencent_qq_ui.CustomerView.SlideView;
import com.reversible.expandablesearchview.widget.ExpandableSearchView;

import java.util.ArrayList;

/**
 * Created by 杰‘z on 2016/8/11.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mImageView;
    private ImageView mSlideMenu;
    private View tabs[];
    private View tab1, tab2, tab3;
    private Fragment fragments[];
    private Fragment tab1fragment;
    private Fragment tab2fragment;
    private Fragment tab3fragment;
    private Toolbar toolbar;
    private TextView mTextView;
    private PopupWindow popupWindow;
    private PopupWindow mPopAdd;
    private ArrayList<PopItem> groups;
    private GroupAdapter groupAdapter;
    private View view;
    private View addPop;
    private ListView lv_group;
    private SlideView slideView;
    private EditText mPopEt;
    private Button mPopSure;
    private Button mPopCancel;
    private AvatarImageView mUserHead;
    private LinearLayout mMyLove;
    private LinearLayout mMyShare;
    private LinearLayout mMyAlbum;
    private LinearLayout mMyFolder;

    private OnAddNoteListener onAddNoteListener;
    private String popString;
    private ExpandableSearchView expandableSearchView;


    public interface OnAddNoteListener {
        public void onAdd(String note);
    }

    public void setAddNoteListener(OnAddNoteListener onAddNoteListener){
        this.onAddNoteListener = onAddNoteListener;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();

    }

    public void initView() {
        mImageView = (ImageView) findViewById(R.id.menu);
        mImageView.setOnClickListener(this);
        mSlideMenu = (ImageView) findViewById(R.id.slide_menu);
        mSlideMenu.setOnClickListener(this);

        slideView = new SlideView(MainActivity.this, null);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = (TextView) findViewById(R.id.title);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tabs = new View[]{tab1, tab2, tab3};
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab1fragment = new Tab1Fragment();
        tab2fragment = new Tab2Fragment();
        tab3fragment = new Tab3Fragment();
        fragments = new Fragment[]{tab1fragment, tab2fragment, tab3fragment};
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentcontain, tab1fragment).
                add(R.id.fragmentcontain, tab2fragment).
                add(R.id.fragmentcontain, tab3fragment).
                commit();
        changeTab(0);

        groups = new ArrayList<PopItem>();

        mUserHead = (AvatarImageView) findViewById(R.id.title_avatar);
        mMyLove = (LinearLayout) findViewById(R.id.mylove);
        mMyShare = (LinearLayout) findViewById(R.id.myshare);
        mMyAlbum = (LinearLayout) findViewById(R.id.myalbum);
        mMyFolder = (LinearLayout) findViewById(R.id.myfolder);
        mMyLove.setOnClickListener(this);
        mMyShare.setOnClickListener(this);
        mMyAlbum.setOnClickListener(this);
        mMyFolder.setOnClickListener(this);
        mUserHead.setOnClickListener(this);

        expandableSearchView = (ExpandableSearchView) findViewById(R.id.expandableSearchView);
        expandableSearchView.setOnSearchActionListener(new ExpandableSearchView.OnSearchActionListener() {
            @Override
            public void onSearchAction(String text) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
            }
        });
    }

    private void changeTab(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < tabs.length; i++) {
            if (i == position) {
                if (i == 0) {
                    setHeaderAndMenu("我的随笔");
                    if (expandableSearchView != null) {
                        expandableSearchView.setVisibility(View.GONE);
                    }
                } else if (i == 1) {
                    setHeader("广场");
                    expandableSearchView.setVisibility(View.VISIBLE);
                } else if (i == 2) {
                    setHeader("设置");
                    if (expandableSearchView != null) {
                        expandableSearchView.setVisibility(View.GONE);
                    }
                }
                tabs[i].setSelected(true);
                transaction.show(fragments[i]);
            } else {
                tabs[i].setSelected(false);
                transaction.hide(fragments[i]);
            }
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1:
                changeTab(0);
                break;
            case R.id.tab2:
                changeTab(1);
                break;
            case R.id.tab3:
                changeTab(2);
                break;
            case R.id.menu:
                showWindow(v);
                break;
            case R.id.pop_bt_ok:
                popString = mPopEt.getText().toString();
                onAddNoteListener.onAdd(popString);
                mPopEt.setText("");
                mPopAdd.dismiss();
                break;
            case R.id.pop_bt_cancel:
                mPopEt.setText("");
                mPopAdd.dismiss();
                break;
            case R.id.title_avatar:
                next(LoginActivity.class);
                break;
            case R.id.mylove:
                next(FindPassActivity.class);
                break;
            case R.id.myshare:
                next(FindPassActivity.class);
                break;
            case R.id.myalbum:
                next(FindPassActivity.class);
                break;
            case R.id.myfolder:
                next(FindPassActivity.class);
                break;
            default:
                break;
        }
    }

    public void setHeader(String str) {
        mTextView.setText(str);
        toolbar.setTitle(null);
        toolbar.setNavigationIcon(null);
        mImageView.setVisibility(View.GONE);

    }

    public void setHeaderAndMenu(String str) {
        mTextView.setText(str);
        toolbar.setTitle(null);
        toolbar.setNavigationIcon(null);
        mSlideMenu.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.VISIBLE);
    }

    private void showWindow(final View parent) {

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.popwindow, null);

            lv_group = (ListView) view.findViewById(R.id.lv_group);

            loadGroup();
            groupAdapter = new GroupAdapter(this, groups);
            lv_group.setAdapter(groupAdapter);
            // 创建一个PopuWidow对象
            popupWindow = new PopupWindow(view, 180, 260);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
//        int xPos = 500;

//        Log.i("coder", "windowManager.getDefaultDisplay().getWidth()/2:"
//                + windowManager.getDefaultDisplay().getWidth() / 2);
//
//        Log.i("coder", "popupWindow.getWidth()/2:" + popupWindow.getWidth() / 2);
//
//        Log.i("coder", "xPos:" + xPos);

        popupWindow.showAsDropDown(parent, xPos, 0);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                switch (position){
                    case 0:
                        popupWindow.dismiss();
                        showAddPop(parent);
                        break;
                    case 1:
                        Intent intent0 = new Intent("android.media.action.IMAGE_CAPTURE");
                        startActivity(intent0);
                        break;
                    case 2:
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                        break;
                    case 3:
//                        Intent intent1 = new Intent("android.intent.action.GET_CONTENT");
                        Intent intent1 = new Intent();
                        intent1.setAction(Intent.ACTION_GET_CONTENT);
                        intent1.setType("image/*");  // 查看类型，比如视频则替换成 video/*，或 */*
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }

                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void showAddPop (View parent) {

        if (mPopAdd == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            addPop = layoutInflater.inflate(R.layout.addpop, null);

            mPopEt = (EditText) addPop.findViewById(R.id.pop_ed_title);
            mPopSure = (Button) addPop.findViewById(R.id.pop_bt_ok);
            mPopCancel = (Button) addPop.findViewById(R.id.pop_bt_cancel);
            mPopSure.setOnClickListener(this);
            mPopCancel.setOnClickListener(this);

            // 创建一个PopuWidow对象
            mPopAdd = new PopupWindow(addPop, 300, 180);
        }

        // 使其聚集
        mPopAdd.setFocusable(true);
        // 设置允许在外点击消失
        mPopAdd.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        mPopAdd.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
//        int xPos = windowManager.getDefaultDisplay().getWidth() / 2 - mPopAdd.getWidth() / 2;

        mPopAdd.showAtLocation(parent, Gravity.CENTER, 0, 0);
//        mPopAdd.showAsDropDown(parent,
//                                DensityUtils.getWidth() / 2,
//                                 DensityUtils.getHeight() / 2);


    }

    private void loadGroup() {

        PopItem popItem1 = new PopItem("新建随笔", R.drawable.plus);
        groups.add(popItem1);
        PopItem popItem2 = new PopItem("扫一扫", R.drawable.scan);
        groups.add(popItem2);
        PopItem popItem3 = new PopItem("打开相机", R.drawable.camera);
        groups.add(popItem3);
        PopItem popItem4 = new PopItem("上传图片", R.drawable.image);
        groups.add(popItem4);
    }

}
