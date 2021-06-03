package com.sandsteam.dencalc2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import AddOn.Barang;
import AddOn.SharedPref;
import AddOn.Storage;
import AddOn.User;
import AddOn.VolleyManage;


public class ListBarangFragment extends Fragment {

    private View view;
    private User user;
    private Button frList_buttonTambah;
    private TextView frList_text_golonganInfo;
    private RecyclerView frList_recyclerViewBarang;
    private static ArrayList<Barang> barangs;
    private RVAdapter rvAdapter;
    private Button frList_button_hitungBiaya;

    private Storage gate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_barang, container, false);
        initView();
        setRV();
        loadDB();
        setListen();
        return view;
    }

    private void initView() {
        frList_recyclerViewBarang = view.findViewById(R.id.frList_recyclerViewBarang);
        frList_button_hitungBiaya = view.findViewById(R.id.frList_button_hitungBiaya);
        frList_text_golonganInfo = view.findViewById(R.id.frList_text_golonganInfo);

        barangs = new ArrayList<>();
        String golongan = Storage.choosenGolongan[0] + " | " + Storage.choosenGolongan[1] + " Volt Ampere | " + Storage.choosenGolongan[2]+"/kWH";

        rvAdapter = new RVAdapter(barangs);
        user = SharedPref.getInstance(getActivity()).getUser();
        frList_text_golonganInfo.setText(golongan);
        frList_buttonTambah = view.findViewById(R.id.frList_buttonTambah);
    }

    private void setRV() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getBaseContext());
        frList_recyclerViewBarang.setLayoutManager(manager);
        frList_recyclerViewBarang.setAdapter(rvAdapter);
    }

    private void setListen(){
        frList_buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TambahActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        frList_button_hitungBiaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FragmentActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                Toast.makeText(getContext(), "Calculated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDB() {
        final String id = String.valueOf(user.getId());
        String url = "http://54.145.5.105/read-item.php";
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray temp = obj.getJSONArray("items");
                            for (int i = 0; i < temp.length(); i++) {
                                JSONObject obj1 = temp.getJSONObject(i);
                                Barang barangBaru = new Barang();
                                barangBaru.setId(obj1.getInt("id"));
                                barangBaru.setTipe_barang(obj1.getString("tipe_barang"));
                                barangBaru.setWatt_barang(obj1.getInt("watt_barang"));
                                barangBaru.setTotal_pemakaian(obj1.getInt("total_pemakaian"));
                                barangBaru.setJumlah(obj1.getInt("jumlah"));
                                barangs.add(barangBaru);
                                Storage.barangs = barangs;
                            }
                            rvAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("id", id);
                return data;
            }
        };
        VolleyManage.getInstance(getActivity()).addToRequestQueue(req);
    }

}