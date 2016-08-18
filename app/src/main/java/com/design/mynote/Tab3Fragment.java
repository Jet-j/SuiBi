package com.design.mynote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by 杰‘z on 2016/8/12.
 */
public class Tab3Fragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private Button mQuit;
    private View view;
    private LinearLayout mVersion;
    private LinearLayout mSuggestion;
    private LinearLayout mUtil;
    private LinearLayout mUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab3, container, false);
        initView();
        return view;

    }

    private void initView() {
        mQuit = (Button) view.findViewById(R.id.quit);
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).next(LoginActivity.class);
                getActivity().finish();
            }
        });
        mVersion = (LinearLayout) view.findViewById(R.id.version);
        mSuggestion = (LinearLayout) view.findViewById(R.id.suggestion);
        mUtil = (LinearLayout) view.findViewById(R.id.util);
        mUpdate = (LinearLayout) view.findViewById(R.id.update);
        mVersion.setOnClickListener(this);
        mSuggestion.setOnClickListener(this);
        mUtil.setOnClickListener(this);
        mUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.version:
                ((BaseActivity)getActivity()).next(FindPassActivity.class);
                break;
            case R.id.suggestion:
                ((BaseActivity)getActivity()).next(FindPassActivity.class);
                break;
            case R.id.util:
                ((BaseActivity)getActivity()).next(FindPassActivity.class);
                break;
            case R.id.update:
                ((BaseActivity)getActivity()).next(FindPassActivity.class);
                break;
            default:
                break;
        }
    }
}
