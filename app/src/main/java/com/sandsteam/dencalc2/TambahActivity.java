package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import AddOn.SharedPref;
import AddOn.User;
import AddOn.VolleyManage;

public class TambahActivity extends AppCompatActivity {

    private Button tambah_button;
    private Toolbar tambah_toolbar;
    private Spinner tambah_spinnerTipe, tambah_spinnerWatt;
    private String[] tipe_barang, watt_barang;
    private ArrayAdapter arrayAdapter_tipe, arrayAdapter_watt;
    private User user;
    private String temp_tipe, temp_watt;
    private EditText tambah_textPakai, tambah_textJumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        initView();
        initAdap();
        setListen();
    }

    private void initView(){
        tambah_toolbar = findViewById(R.id.tambah_toolbar);
        tambah_spinnerTipe = findViewById(R.id.tambah_spinnerTipe);
        tambah_spinnerWatt = findViewById(R.id.tambah_spinnerWatt);
        tipe_barang = getResources().getStringArray(R.array.tipe_barang);
        tambah_button = findViewById(R.id.tambah_button);
        tambah_textPakai = findViewById(R.id.tambah_textPakai);
        tambah_textJumlah = findViewById(R.id.tambah_textJumlah);
        user = SharedPref.getInstance(this).getUser();
    }

    private void initAdap(){
        arrayAdapter_tipe = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, tipe_barang);
        arrayAdapter_tipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tambah_spinnerTipe.setAdapter(arrayAdapter_tipe);

    }
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    private void setListen(){
        tambah_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        tambah_spinnerTipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    watt_barang = getResources().getStringArray(R.array.watt_lampu);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }else if (position == 1){
                    watt_barang = getResources().getStringArray(R.array.watt_rcook);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }else if (position == 2){
                    watt_barang = getResources().getStringArray(R.array.watt_kulkas);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }else if (position == 3){
                    watt_barang = getResources().getStringArray(R.array.watt_ac);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }else if (position == 4){
                    watt_barang = getResources().getStringArray(R.array.watt_setrika);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }else if (position == 5){
                    watt_barang = getResources().getStringArray(R.array.watt_mcuci);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }else if (position == 6){
                    watt_barang = getResources().getStringArray(R.array.watt_kangin);
                    arrayAdapter_watt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,watt_barang);
                }
                temp_tipe = parent.getItemAtPosition(position).toString();
                arrayAdapter_watt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                tambah_spinnerWatt.setAdapter(arrayAdapter_watt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tambah_spinnerWatt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp_watt = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tambah_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
    }

    private void process(){
        final String tipe = temp_tipe;
        final String watt = temp_watt;
        final String id_user = String.valueOf(user.getId());
        final String jumlah = tambah_textJumlah.getEditableText().toString().trim();
        final String pakai = tambah_textPakai.getEditableText().toString().trim();
        String url = "http://54.145.5.105/add-item.php";
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
                data.put("tipe_barang",tipe);
                data.put("jumlah",jumlah);
                data.put("id_user",id_user);
                data.put("watt_barang",watt);
                data.put("total_pemakaian",pakai);
                return data;
            }
        };
        VolleyManage.getInstance(this).addToRequestQueue(req);
    }
}