package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HomeFragment extends Fragment {

    private View home_view;

    private Spinner frhome_spinerGolongan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        home_view = inflater.inflate(R.layout.fragment_home, container, false);

        frhome_spinerGolongan = (Spinner) home_view.findViewById(R.id.frhome_spinerGolongan);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(HomeFragment.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tesData_golongan));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frhome_spinerGolongan.setAdapter(myAdapter);

        return home_view;
    }
}