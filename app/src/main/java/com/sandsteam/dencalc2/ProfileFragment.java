package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfileFragment extends Fragment {
    private View view;
    private TextView profile_nama;
    private Button profile_logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView();
        return view;
    }
    private void initView(){
        profile_nama = view.findViewById(R.id.profile_nama);
        profile_logoutButton = view.findViewById(R.id.profile_logoutButton);
    }
}