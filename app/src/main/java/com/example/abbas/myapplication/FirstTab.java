package com.example.abbas.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import mklib.hosseini.com.vinci.Vinci;


/**
 * Created by abbas on 8/24/15.
 */
public class FirstTab extends android.support.v4.app.Fragment {
    private static final String TAB_POSITION = "tab_position";
    private static final String TITLE = "TITLE";

    public static FirstTab newInstance(int tabPosition) {
        FirstTab fragment = new FirstTab();
        Bundle args = new Bundle();
        args.putInt(TAB_POSITION, tabPosition);
        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(
                R.layout.fragment_list_view, container, false);
        final RecyclerView recyclerView =
                (RecyclerView) v.findViewById(R.id.recyclerview);

//        String[] urls = {
//                "http://192.168.43.8/VinCi/Mona_Lisa1.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa2.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa3.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa4.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa5.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa6.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa7.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa8.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa9.jpg"
//        };
//
//        String[] urls = {
//                "http://192.168.43.8/VinCi/Mona_Lisa1.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa2.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa3.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa4.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa5.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa6.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa7.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa8.jpg",
//                "http://192.168.43.8/VinCi/Mona_Lisa9.jpg"
//        };
        List<File> files = Vinci.base(getContext()).process().files().list();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new tab1(files));
        return v;

    }
}
