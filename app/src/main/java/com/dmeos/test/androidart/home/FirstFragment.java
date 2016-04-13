package com.dmeos.test.androidart.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmeos.test.androidart.R;
import com.dmeos.test.androidart.base.BaseFregment;


public class FirstFragment extends BaseFregment {
    private static final String TAG = FirstFragment.class.getSimpleName();

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "FirstFragment----onCreateView-----");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }


}