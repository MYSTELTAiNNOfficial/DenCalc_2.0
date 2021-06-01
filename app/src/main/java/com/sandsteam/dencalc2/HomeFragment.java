package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import AddOn.Barang;
import AddOn.Storage;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;
    private Storage gate;

    private TextView frhome_editText_totalBiaya;
    private Spinner frhome_spinerGolongan;
    private String total_Biaya, golongan;
    private ArrayAdapter<CharSequence> myAdapter;
    private ArrayList<Barang> barangs;
    private double rupiah_golongan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();

        return view;
    }

    private void initView() {
        frhome_editText_totalBiaya = view.findViewById(R.id.frhome_editText_totalBiaya);
        frhome_spinerGolongan = (Spinner) view.findViewById(R.id.frhome_spinerGolongan);
        gate = new Storage();
        barangs = gate.getListBarangs();
        golongan = gate.getGolongan();
        //Spinner Golongan
        myAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.tesData_golongan, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frhome_spinerGolongan.setAdapter(myAdapter);
        //------------------------------------------------------------------------------------\\
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getContext(),"Saya Memilih "+myAdapter.getItem(position), Toast.LENGTH_SHORT).show();
        golongan = myAdapter.getItem(position).toString();
        Toast.makeText(getContext(),"Golongan: "+golongan, Toast.LENGTH_SHORT).show();
        if(golongan == "R-1/TR (450VA - Rp. 165/KWH)"){
            rupiah_golongan = 165;
        }else if(golongan == "R-1/TR (900VA - Rp. 274/KWh)"){
            rupiah_golongan = 274;
        }else if(golongan == "R-1M/TR (900VA - Rp. 1352/KWh)"){
            rupiah_golongan = 1352;
        }else if(golongan == "R-1/TR (1300VA - Rp. 1444.70/KWh)"){
            rupiah_golongan = 1444.70;
        }else if(golongan == "R-1/TR (2200VA - Rp. 1444.70/KWh)"){
            rupiah_golongan = 1444.70;
        }else if(golongan == "R-2/TR (5500VA - Rp. 1444.70/KWh)"){
            rupiah_golongan = 1444.70;
        }else if(golongan == "R-3/TR (> 5500VA - Rp. 1444.70/KWh)") {
            rupiah_golongan = 1444.70;
        }
        frhome_editText_totalBiaya.setText(hitungBiaya(barangs, rupiah_golongan));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(),"Tidak memilih apapun", Toast.LENGTH_SHORT).show();
    }

    public String hitungBiaya(ArrayList<Barang> barangs, double rupiah_golongan){
        double wattHours = 0;
        double kiloWattHours, hari, bulan;
        for(int i = 0; i < barangs.size(); i++){
            Barang barangTemp = barangs.get(i);
            double wattTemp = (double) barangTemp.getWatt_barang();
            double pemakaianTemp = (double)barangTemp.getTotal_pemakaian();
            double jumlahTemp = (double) barangTemp.getJumlah();
            double temp = wattTemp*pemakaianTemp*jumlahTemp;
            wattHours += temp;
        }
        kiloWattHours = wattHours / 1000;
        System.out.println("Total pemakaian daya listrik : " + kiloWattHours + " KWh");
        hari = kiloWattHours * rupiah_golongan;
        bulan = hari * 30;
        total_Biaya = String.valueOf(bulan);
        return total_Biaya;
    }

}