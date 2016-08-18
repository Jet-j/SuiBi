package com.design.mynote;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 杰‘z on 2016/8/13.
 */
public class NoteContentActivity extends BaseActivity implements View.OnClickListener {

    private Note mNote;
    private EditText mContent;
    private String lastContent;
    private TextView lastTime;
    private TextView mTextView;
    private Toolbar toolbar;
    private ImageView mImageView;
    private PopupWindow popupWindow;
    private ArrayList<PopItem> groups;
    private GroupAdapter groupAdapter;
    private View view;
    private ListView lv_group;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private NoteDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notecontent);
        initView();
        setHeaderAndMenu(mNote.note);
    }

    @Override
    public void initView() {
        mNote = (Note)getIntent().getSerializableExtra("note");
        db = NoteDB.getInstance(this);
        mSharedPreferences = getSharedPreferences("savecontent", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        lastContent = "";
        mContent = (EditText) findViewById(R.id.et_notecontent);
//        lastContent = mSharedPreferences.getString("lastcontent", "");
        lastContent = db.queryNote(mNote).getContent();
        mContent.setEnabled(true);
        if (lastContent != null) {
            mContent.setText(lastContent);
            mContent.setSelection(lastContent.length());
        }
        lastTime = (TextView) findViewById(R.id.et_time);
//        String time = mSharedPreferences.getString("lasttime", "");
        String time = db.queryNote(mNote).getLastTime();
        if (time != null) {
            lastTime.setText(time);
        }else {
            lastTime.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date(System.currentTimeMillis())));
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = (TextView) findViewById(R.id.title);
        mImageView = (ImageView) findViewById(R.id.menu);
        groups = new ArrayList<PopItem>();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu:
                showWindow(v);
                break;
            default:
                break;
        }
    }

    public void setHeaderAndMenu (String str) {

        mTextView.setText(str);
        toolbar.setTitle(null);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentContent = mContent.getText().toString();
//                Log.i("TAG", "currentContent : " + mContent.getText().toString());
//                Log.i("TAG", "lastContent : " + lastContent);
//                Log.i("TAG", "equals : " + currentContent.equals(lastContent));
                if (!currentContent.equals(lastContent)) {
                    mNote.content = currentContent;
                    String nowTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date(System.currentTimeMillis()));
                    mNote.lastTime = nowTime;
                    db.updateNote(mNote);
                }
                finish();
            }
        });
        mImageView.setVisibility(View.VISIBLE);
        mImageView.setOnClickListener(this);
    }

    private void showWindow(View parent) {

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.popwindow, null);

            lv_group = (ListView) view.findViewById(R.id.lv_group);

            loadGroup();
            groupAdapter = new GroupAdapter(this, groups);
            lv_group.setAdapter(groupAdapter);
            // 创建一个PopuWidow对象
            popupWindow = new PopupWindow(view, 150, 205);
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

                        String currentContent = mContent.getText().toString();
                        if (currentContent.equals(lastContent)) {
                            ToastUtils.showToast("保存成功");
                        } else{
                            mNote.content = currentContent;
                            String nowTime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date(System.currentTimeMillis()));
                            mNote.lastTime = nowTime;
                            db.updateNote(mNote);
//                            mEditor.putString("content", currentContent);
//                            mEditor.putString("lastcontent", mNote.content);
//                            mEditor.putString("lasttime", nowTime);
//                            mEditor.commit();
                            ToastUtils.showToast("保存成功");
                        }
                        break;
                    case 1:
                        next(FindPassActivity.class);
                        break;
                    case 2:
                        next(FindPassActivity.class);
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

    private void loadGroup() {

        PopItem popItem1 = new PopItem("保存", R.drawable.save);
        groups.add(popItem1);
        PopItem popItem2 = new PopItem("分享", R.drawable.share);
        groups.add(popItem2);
        PopItem popItem3 = new PopItem("喜欢", R.drawable.love);
        groups.add(popItem3);
    }
}
