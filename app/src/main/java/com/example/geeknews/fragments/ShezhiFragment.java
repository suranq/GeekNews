package com.example.geeknews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShezhiFragment extends Fragment {


    public ShezhiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shezhi, container, false);
    }

}
