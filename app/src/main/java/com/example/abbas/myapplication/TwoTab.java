package com.example.abbas.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;


/**
 * Created by abbas on 8/24/15.
 */
public class TwoTab extends android.support.v4.app.Fragment {
    private static final String TAB_POSITION = "tab_position";
    private static final String TITLE = "TITLE";

    public static TwoTab newInstance(int tabPosition) {
        TwoTab fragment = new TwoTab();
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

        String[] urls = {
                "http://192.168.43.8/VinCi/Parts/Mona_Lisa.jpg",
                "http://192.168.43.8/VinCi/Parts/417px-Leonardo_da_Vinci_-_Angelo_Incarnato.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_da_Vinci_-_presumed_self-portrait_-_WGA12798.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_da_Vinci_Madonna_of_the_Carnation.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_-_St._Anne_cartoon.jpg",
                "http://192.168.43.8/VinCi/Parts/479px-Vergine_delle_Rocce_cheramy.jpg",
                "http://192.168.43.8/VinCi/Parts/382px-Ambrogio_de_Predis_-_Portrait_of_a_Woman_-_WGA18378.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_da_Vinci_Benois_Madonna.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_da_Vinci_-_Portrait_of_a_Musician.jpg",
                "http://192.168.43.8/VinCi/Parts/459px-Leonardo,_san_girolamo.jpg",
                "http://192.168.43.8/VinCi/Parts/Verrocchio_workshop_-_Tobias_and_the_Angel_-_NG.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_da_Vinci_-_Saint_John_the_Baptist_C2RMF_retouched.jpg",
                "http://192.168.43.8/VinCi/Parts/422px-Leonardo_da_Vinci_LUCAN_self-portrait_PORTRAIT.jpg",
                "http://192.168.43.8/VinCi/Parts/443px-Profile_of_a_Young_Fiancee_-_da_Vinci.jpg",
                "http://192.168.43.8/VinCi/Parts/509px-Leonardo_da_Vinci_-_Female_head_(La_Scapigliata)_-_WGA12716.jpg",
                "http://192.168.43.8/VinCi/Parts/Leonardo_da_Vinci_(attrib)-_la_Belle_Ferroniere.jpg",
                "http://192.168.43.8/VinCi/Parts/501px-Christ_Carrying_the_Cross_(cropped).jpg",
                "http://192.168.43.8/VinCi/Parts/441px-Isabella_d'este.jpg",
                "http://192.168.43.8/VinCi/Parts/Madonna_of_the_Yarnwinder.jpg"
        };

        List<String> list = Arrays.asList(urls);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new tab2(list));
        return v;

    }
}
