package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class EditBarangActivity extends AppCompatActivity {

    private User user;

    private TextView update_textTipe, update_textWatt;
    private EditText update_textPakai, update_textJumlah;
    private Button edit_button;
    private Toolbar edit_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        String id_item = getIntent().getStringExtra("id_item");
        int index = getIntent().getIntExtra("index", 0);

        initView();
        setListen();
    }

    private void setListen() {
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


                editProcess();
            }
        });

    }

    private void initView() {
        update_textTipe = findViewById(R.id.edit_textTipe);
        update_textWatt = findViewById(R.id.edit_textWatt);
        update_textPakai = findViewById(R.id.tambah_textPakai);
        update_textJumlah = findViewById(R.id.tambah_textJumlah);
        edit_button = findViewById(R.id.edit_button);
        edit_toolbar = findViewById(R.id.edit_toolbar);

        loadDB();
    }

    private void loadDB(){
        String url = "http://54.145.5.105/readbyid-item.php";
        StringRequest myReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray temp = obj.getJSONArray("items");
                            JSONObject obj1 = temp.getJSONObject(10);
                            update_textTipe.setText(obj1.getString("tipe_barang"));
                            update_textWatt.setText(String.valueOf(obj1.getInt("watt_barang")));
                            update_textPakai.setText(String.valueOf(obj1.getInt("total_pemakaian")));
                            update_textJumlah.setText(String.valueOf(obj1.getInt("jumlah_barang")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void editProcess(){
        final String id_user = String.valueOf(user.getId());
//        final String tipe = ;
//        final String watt = ;
        final String jumlah = update_textJumlah.getEditableText().toString().trim();
        final String pakai = update_textPakai.getEditableText().toString().trim();
        String url = "http://54.145.5.105/update-item.php";
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
    //            data.("id", id_item);
                return super.getParams();
            }
        };

        }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}