package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import AddOn.SharedPref;
import AddOn.User;
import AddOn.VolleyManage;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout regis_textInputUser, regis_textInputPass,regis_textInputPass2;
    private Button regis_button;
    private TextView login_clickToLogin;
    private Toolbar regis_toolbar;
    private boolean cekuser,cekpass,cekpass2 =false;

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

        regis_textInputUser.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = regis_textInputUser.getEditText().getText().toString().trim();
                if (username == "") {
                    regis_textInputUser.setError("Masukkan Username");
                    cekuser = false;
                }else{
                    regis_textInputUser.setError(null);
                    cekuser=true;
                }
            }
        });

        regis_textInputPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = regis_textInputPass.getEditText().getText().toString().trim();
                if (password.length() < 8 || password.length() > 20) {
                    regis_textInputPass.setError("Password must be 8 to 20 characters");
                    cekpass = false;
                }else{
                    regis_textInputPass.setError(null);
                    cekpass = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password2 = regis_textInputPass2.getEditText().getText().toString().trim();
                String password = regis_textInputPass.getEditText().getText().toString().trim();
                if (password == "") {
                    regis_textInputPass.setError("Masukkan Password");
                    cekpass = false;
                }else{
                    regis_textInputPass.setError(null);
                    cekpass = true;
                }
                if (password != password2){
                    regis_textInputPass.setError("Password tidak sama!");
                    cekpass = false;
                }else{
                    regis_textInputPass.setError(null);
                    cekpass = true;
                }
                if(cekuser == true && cekpass == true && cekpass2 == true){
                    regis_button.setEnabled(true);
                }
            }
        });
        regis_textInputPass2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password2 = regis_textInputPass2.getEditText().getText().toString().trim();
                if (password2.length() < 8 || password2.length() > 20) {
                    regis_textInputPass.setError("Password must be 8 to 20 characters");
                    cekpass2 = false;
                }else{
                    regis_textInputPass.setError(null);
                    cekpass2 = true;
                }
                if(cekuser == true && cekpass == true && cekpass2 == true){
                    regis_button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password2 = regis_textInputPass2.getEditText().getText().toString().trim();
                String password = regis_textInputPass.getEditText().getText().toString().trim();
                if (password2 == "") {
                    regis_textInputPass2.setError("Masukkan Password");
                    cekpass2 = false;
                }else{
                    regis_textInputPass2.setError(null);
                    cekpass2 = true;
                }
                if(cekuser == true && cekpass == true && cekpass2 == true){
                    regis_button.setEnabled(true);
                }
            }
        });
        regis_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
    }
    private void process(){
        String url = "http://192.168.1.68/API/register.php";
        final String username = regis_textInputUser.getEditText().getText().toString().trim();
        final String password = regis_textInputPass.getEditText().getText().toString().trim();
        StringRequest myReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (!obj.getBoolean("err")) {
                        Toast.makeText(getApplicationContext(), obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), obj.getString("Message2"), Toast.LENGTH_SHORT).show();
                        JSONObject userdata = obj.getJSONObject("user");
                        User user = new User(
                                userdata.getInt("id"),
                                userdata.getString("username")
                        );
                        SharedPref.getInstance(getApplicationContext()).userLogin(user);
                        //starting the profile activity
                        startActivity(new Intent(getApplicationContext(), FragmentActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        VolleyManage.getInstance(this).addToRequestQueue(myReq);
    }
}