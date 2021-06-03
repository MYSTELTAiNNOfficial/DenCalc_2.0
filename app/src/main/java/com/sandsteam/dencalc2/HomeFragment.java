package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import AddOn.Barang;
import AddOn.Storage;

public class HomeFragment extends Fragment{

    private View view;

    private Spinner frhome_spinerGolongan;
    private Button frhome_simpanButton;
    private ArrayAdapter<CharSequence> myAdapter;
    private ArrayList<Barang> barangs;
    private TextView frhome_editText_totalBiaya, choosenGolongan, choosenVA, choosenRupiah;

    private String[] tempGolongan = Storage.choosenGolongan;
    private String nama_golongan = "", va_golongan = "", toString_rupiahGolongan = "",total_Biaya;
    private double rupiah_golongan = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        setListen();
        return view;
    }

    private void setListen() {
        frhome_simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frhome_editText_totalBiaya.setText((hitungBiaya(barangs, rupiah_golongan)));
                tempGolongan [0] = nama_golongan;
                tempGolongan [1] = va_golongan;
                tempGolongan [2] = "Rp "+ rupiah_golongan +",-";
                Toast.makeText(getContext(), "Golongan Dipilih: "+tempGolongan[0], Toast.LENGTH_SHORT).show();
            }
        });

        frhome_spinerGolongan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    nama_golongan = "R-1/TR";
                    va_golongan = "450";
                    rupiah_golongan = 165;
//                    tempGolongan = String.valueOf(parent.getItemAtPosition(position));
                }else if(position == 1){
                    nama_golongan = "R-1/TR";
                    va_golongan = "900";
                    rupiah_golongan = 274;
//                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 2){
                    nama_golongan = "R-1M/TR";
                    va_golongan = "900";
                    rupiah_golongan = 1352;
//                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 3){
                    nama_golongan = "R-1/TR";
                    va_golongan = "1300";
                    rupiah_golongan = 1444.70;
//                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 4){
                    nama_golongan = "R-1/TR";
                    va_golongan = "2200";
                    rupiah_golongan = 1444.70;
//                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 5){
                    nama_golongan = "R-2/TR";
                    va_golongan = "5500";
                    rupiah_golongan = 1444.70;
//                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 6) {
                    nama_golongan = "R-3/TR";
                    va_golongan = "> 5500";
                    rupiah_golongan = 1444.70;
//                    tempGolongan = parent.getItemAtPosition(position).toString();
                }
                toString_rupiahGolongan = "Rp "+String.valueOf(rupiah_golongan)+"/kWH";
                choosenGolongan.setText(nama_golongan);
                choosenVA.setText(va_golongan + " Volt Ampere");
                choosenRupiah.setText(toString_rupiahGolongan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        frhome_editText_totalBiaya = view.findViewById(R.id.frhome_editText_totalBiaya);
        frhome_spinerGolongan = (Spinner) view.findViewById(R.id.frhome_spinerGolongan);
        frhome_simpanButton = view.findViewById(R.id.frhome_simpanButton);
        choosenGolongan = view.findViewById(R.id.home_choosenGolongan);
        choosenVA = view.findViewById(R.id.home_choosenVA);
        choosenRupiah = view.findViewById(R.id.home_choosenRupiah);

        barangs = Storage.barangs;
        //Spinner Golongan
        myAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.tesData_golongan, android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frhome_spinerGolongan.setAdapter(myAdapter);
        //------------------------------------------------------------------------------------\\
    }


    public String hitungBiaya(ArrayList<Barang> barangs, double rupiah_golongan){
        double wattHours = 0;
        double kiloWattHours, hari, bulan;
        for(int i = 0; i < barangs.size(); i++){
            Barang barangTemp = barangs.get(i);
            double wattTemp = (double) barangTemp.getWatt_barang();
            double pemakaianTemp = (double) barangTemp.getTotal_pemakaian();
            double jumlahTemp = (double) barangTemp.getJumlah();
            double temp = wattTemp*pemakaianTemp*jumlahTemp;
            wattHours += temp;
        }
        Toast.makeText(getContext(), String.valueOf(wattHours), Toast.LENGTH_SHORT).show();
        kiloWattHours = wattHours / 1000;
        hari = kiloWattHours * rupiah_golongan;
        bulan = hari * 30;
        new DecimalFormat("##.##").format(bulan);
        total_Biaya = String.valueOf(bulan);
        return total_Biaya;
    }

}