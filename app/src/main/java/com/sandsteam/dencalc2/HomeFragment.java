package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import AddOn.Barang;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;

    private TextView frhome_editText_totalBiaya;
    private Spinner frhome_spinerGolongan;
    private String total_Biaya;
    private ArrayAdapter<CharSequence> myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        return view;
    }

    private void initView() {
        frhome_editText_totalBiaya = view.findViewById(R.id.frhome_editText_totalBiaya);
        frhome_spinerGolongan = (Spinner) view.findViewById(R.id.frhome_spinerGolongan);

        //Spinner Golongan
        myAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.tesData_golongan, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frhome_spinerGolongan.setAdapter(myAdapter);
        /////////\\\\\\\\\
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}