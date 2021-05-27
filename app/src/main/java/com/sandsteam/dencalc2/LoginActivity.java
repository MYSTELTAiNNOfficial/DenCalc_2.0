package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import AddOn.SharedPref;
import AddOn.User;
import AddOn.VolleyManage;

public class LoginActivity extends AppCompatActivity {

    private TextView login_clickToRegis;
    private TextInputLayout login_textInputUser, login_textInputPass;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPref.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        initView();
        setListen();
    }

    private void initView() {
        login_clickToRegis = findViewById(R.id.login_clickToRegis);
        login_textInputUser = findViewById(R.id.login_textInputUser);
        login_textInputPass = findViewById(R.id.login_textInputPass);
        login_button = findViewById(R.id.login_button);
    }

    private void setListen() {
        login_clickToRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        login_textInputUser.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = login_textInputUser.getEditText().getText().toString().trim();
                if (username == "") {
                    login_textInputUser.setError("Masukkan Username anda");
                }else{
                    login_textInputUser.setError(null);
                }
            }
        });
        login_textInputPass.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = login_textInputPass.getEditText().getText().toString().trim();
                if (password.length() < 8 || password.length() > 20) {
                    login_textInputPass.setError("Password must be 8 to 20 characters");
                }else{
                    login_textInputPass.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = login_textInputPass.getEditText().getText().toString().trim();
                if (password == "") {
                    login_textInputPass.setError("Masukkan Password anda");
                }else{
                    login_textInputPass.setError(null);
                }
            }
        });
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });

    }

    private void process() {
        String url = "http://192.168.1.68/API/login.php";
        final String username = login_textInputUser.getEditText().getText().toString().trim();
        final String password = login_textInputPass.getEditText().getText().toString().trim();
        StringRequest myReq  = new StringRequest (Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("err")) {
                                Toast.makeText(getApplicationContext(), obj.getString("Message"), Toast.LENGTH_SHORT).show();
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