package com.sandsteam.dencalc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class EditBarangActivity extends AppCompatActivity {

    private TextView update_textTipe, update_textWatt;
    private EditText update_textPakai, update_textJumlah;
    private Button update_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        initView();
        setListen();

    }

    private void setListen() {
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tempPakai = update_textPakai.getText().toString().trim();
                String tempJumlah = update_textJumlah.getText().toString().trim();
            }
        });

    }

    private void initView() {
        update_textTipe = findViewById(R.id.edit_textTipe);
        update_textWatt = findViewById(R.id.edit_textWatt);
        update_textPakai = findViewById(R.id.tambah_textPakai);
        update_textJumlah = findViewById(R.id.tambah_textJumlah);
        update_button = findViewById(R.id.edit_button);
    }



//    private void editProcess(){
//        String url = "http://54.145.5.105/update-item.php";
//        StringRequest req = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                };
//
//        }
//

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}