package com.sandsteam.dencalc2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import AddOn.Barang;
import AddOn.SharedPref;
import AddOn.User;
import AddOn.VolleyManage;


public class ListBarangFragment extends Fragment {

    private View view;
    private User user = SharedPref.getInstance(getActivity()).getUser();
    private RecyclerView frList_recyclerViewBarang;
    private ArrayList<Barang> barangs;
    private RVAdapter rvAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list_barang, container, false);

        initView();
        setRV();
        loadDB();
        return view;
    }

    private void initView() {
        frList_recyclerViewBarang = view.findViewById(R.id.frList_recyclerViewBarang);
        barangs = new ArrayList<Barang>();
        rvAdapter = new RVAdapter(barangs);
    }

    private void setRV() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity().getBaseContext());
        frList_recyclerViewBarang.setLayoutManager(manager);
        frList_recyclerViewBarang.setAdapter(rvAdapter);
    }

    private void loadDB() {
        final String id = String.valueOf(user.getId());
        String url = "http://192.168.1.68/API/read-item.php";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItem = response.getJSONArray("items");
                            for (int i = 0; i < jsonItem.length(); i++) {
                                JSONObject obj = jsonItem.getJSONObject(i);
                                Barang barangBaru = new Barang();
                                barangBaru.setId(obj.getInt("id"));
                                barangBaru.setTipe_barang(obj.getString("tipe_barang"));
                                barangBaru.setJumlah(obj.getInt("jumlah"));
                                barangBaru.setTotal_pemakaian(obj.getInt("total_pemakaian"));
                                barangBaru.setWatt_barang(obj.getInt("watt_barang"));
                                barangs.add(barangBaru);
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

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };
        VolleyManage.getInstance(getActivity()).addToRequestQueue(req);
    }
}