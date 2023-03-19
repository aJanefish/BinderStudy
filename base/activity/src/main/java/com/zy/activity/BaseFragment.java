package com.zy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.zlog.ZLog;

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    protected final Handler mHandler = new Handler();
    protected float stepOne;
    protected float widthPixels;
    protected float heightPixels;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDimen();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayout(), container, false);
    }

    protected int getLayout() {
        return R.layout.fragment_base;
    }

    protected boolean checkActivityDestroyed() {
        if (getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed()) {
            ZLog.e(TAG, "getActivity() == null || getActivity().isFinishing() || getActivity().isDestroyed()");
            return true;
        }
        return false;
    }

    private void initDimen() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;

        float dp_8 = getResources().getDimension(R.dimen.dp_16);

        ZLog.d(TAG, "displayMetrics:" + displayMetrics);
        ZLog.d(TAG, "heightPixels:" + heightPixels);
        ZLog.d(TAG, "widthPixels:" + widthPixels);
        ZLog.d(TAG, "dp_8:" + dp_8);
        stepOne = (widthPixels - dp_8) / 10;
        ZLog.d(TAG, "stepOne:" + stepOne);
    }


}