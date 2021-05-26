package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout regis_textInputUser, regis_textInputPass,regis_textInputPass2;
    private Button regis_button;
    private TextView login_clickToLogin;
    private Toolbar regis_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setListen();
    }

    private void  initView(){
        regis_textInputUser = findViewById(R.id.regis_textInputUser);
        regis_textInputPass = findViewById(R.id.regis_textInputPass);
        regis_textInputPass2 = findViewById(R.id.regis_textInputPass2);
        regis_button = findViewById(R.id.regis_button);
        login_clickToLogin = findViewById(R.id.login_clickToLogin);
        regis_toolbar = findViewById(R.id.regis_toolbar);
    }
    private void setListen(){
        login_clickToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regis_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}