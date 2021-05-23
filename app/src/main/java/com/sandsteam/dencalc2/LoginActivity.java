package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private TextView login_clickToRegis;
    private TextInputLayout login_textInputUser,login_textInputPass;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setListen();

    }
    private void initView(){
        login_clickToRegis = findViewById(R.id.login_clickToRegis);
        login_textInputUser = findViewById(R.id.login_textInputUser);
        login_textInputPass = findViewById(R.id.login_textInputPass);
        login_button = findViewById(R.id.login_button);
    }

    private void setListen(){
        login_clickToRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}