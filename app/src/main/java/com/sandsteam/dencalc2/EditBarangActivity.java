package com.sandsteam.dencalc2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import AddOn.User;
import AddOn.VolleyManage;

public class EditBarangActivity extends AppCompatActivity {
    private TextView update_textTipe, update_textWatt;
    private EditText update_textPakai, update_textJumlah;
    private Button edit_button;
    private Toolbar edit_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        int id = getIntent().getIntExtra("id_item", 0);

        initView();
        loadDB(id);
        setListen(id);
    }

    private void setListen(int id) {
        edit_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProcess(id);
            }
        });
    }

    private void initView() {
        update_textTipe = findViewById(R.id.edit_textTipe);
        update_textWatt = findViewById(R.id.edit_textWatt);
        update_textPakai = findViewById(R.id.edit_textPakai);
        update_textJumlah = findViewById(R.id.edit_textjumlah);
        edit_button = findViewById(R.id.edit_button);
        edit_toolbar = findViewById(R.id.edit_toolbar);

    }

    private void loadDB(int id){
        final String temp = String.valueOf(id);
        String url = "http://178.128.127.125/API/readbyid-item.php";
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONObject temp = obj.getJSONObject("items");
                                update_textTipe.setText(temp.getString("tipe_barang"));
                                update_textWatt.setText(temp.getString("watt_barang"));
                                update_textPakai.setText(temp.getString("total_pemakaian"));
                                update_textJumlah.setText(temp.getString("jumlah"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", temp);
                return data;
            }
        };
        VolleyManage.getInstance(this).addToRequestQueue(req);
    }

    private void editProcess(int id){
        final String temp = String.valueOf(id);
        final String tipe = update_textTipe.getText().toString().trim();
        final String watt = update_textWatt.getText().toString().trim();
        final String jumlah = update_textJumlah.getEditableText().toString().trim();
        final String pakai = update_textPakai.getEditableText().toString().trim();
        String url = "http://178.128.127.125/API/update-item.php";
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", temp);
                data.put("tipe_barang",tipe);
                data.put("jumlah",jumlah);
                data.put("watt_barang",watt);
                data.put("total_pemakaian",pakai);
                return data;
            }
        };
        VolleyManage.getInstance(this).addToRequestQueue(req);
        }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}