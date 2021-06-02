package com.sandsteam.dencalc2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import AddOn.Barang;
import AddOn.VolleyManage;

import static androidx.core.content.ContextCompat.startActivity;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BarangViewHold> {

    private ArrayList<Barang> liistBarang;

    private Context context;

    public RVAdapter(ArrayList<Barang> liistBarang) {
        this.liistBarang = liistBarang;
    }
    @NonNull
    @Override
    public BarangViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.barang_cardview, parent, false);
        context = parent.getContext();
        return new BarangViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.BarangViewHold holder, int position) {
        holder.cv_infoJumlah.setText(String.valueOf(liistBarang.get(position).getJumlah()));
        holder.cv_infoPemakaian.setText(String.valueOf(liistBarang.get(position).getTotal_pemakaian()));
        holder.cv_infoWatt.setText(String.valueOf(liistBarang.get(position).getWatt_barang()));
        holder.cv_kategoriBarang.setText(String.valueOf(liistBarang.get(position).getTipe_barang()));
        holder.index = position;
    }

    @Override
    public int getItemCount() {
        return liistBarang.size();
    }

    public class BarangViewHold extends RecyclerView.ViewHolder {
        private TextView cv_infoWatt, cv_infoJumlah, cv_infoPemakaian, cv_kategoriBarang;
        private ImageView cv_editButton, cv_deleteButton;
        private int index;
        public BarangViewHold(View itemView) {
            super(itemView);
            cv_infoWatt = itemView.findViewById(R.id.cv_infoWatt);
            cv_infoJumlah = itemView.findViewById(R.id.cv_infoJumlah);
            cv_infoPemakaian = itemView.findViewById(R.id.cv_infoPemakaian);
            cv_kategoriBarang = itemView.findViewById(R.id.cv_kategoriBarang);
            cv_editButton = itemView.findViewById(R.id.cv_editButton);
            cv_deleteButton = itemView.findViewById(R.id.cv_deleteButton);

            cv_deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String id_item = String.valueOf(liistBarang.get(index).getId());
                    deleteProcess(id_item, index);
                }
            });

            cv_editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditBarangActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);


                }
            });

        }
    }
    private void deleteProcess(String id_item, int index){
        String url = "http://54.145.5.105/delete-item.php";
        StringRequest req = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        liistBarang.remove(index);
                        notifyItemRemoved(index);
                        notifyItemRangeChanged(index, liistBarang.size());
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
                data.put("id",id_item);
                return data;
            }
        };
        VolleyManage.getInstance(context).addToRequestQueue(req);
    }
}
