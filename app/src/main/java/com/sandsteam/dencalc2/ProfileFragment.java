package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import AddOn.SharedPref;
import AddOn.User;


public class ProfileFragment extends Fragment {
    private View view;
    private TextView profile_nama;
    private Button profile_logoutButton;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView();
        setListen();
        return view;
    }
    private void initView(){
        profile_nama = view.findViewById(R.id.profile_nama);
        profile_logoutButton = view.findViewById(R.id.profile_logoutButton);
        user = SharedPref.getInstance(getActivity()).getUser();
    }
    private void setListen(){
        profile_nama.setText(user.getUsername());
        profile_logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.getInstance(getContext()).logout();
            }
        });
    }
}