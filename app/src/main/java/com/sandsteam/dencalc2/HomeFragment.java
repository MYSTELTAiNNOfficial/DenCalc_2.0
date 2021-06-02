package com.sandsteam.dencalc2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import AddOn.Barang;
import AddOn.Storage;

public class HomeFragment extends Fragment{

    private View view;
    private Storage gate;

    private EditText frhome_editText_totalBiaya;
    private Spinner frhome_spinerGolongan;
    private String total_Biaya, golongan, tempGolongan;
    private ArrayAdapter<CharSequence> myAdapter;
    private ArrayList<Barang> barangs;
    private TextView choosenGolongan, choosenVA, choosenRupiah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initView();
        setListen();
        return view;
    }

    private void setListen() {
        frhome_spinerGolongan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nama_golongan = "", va_golongan = "", toString_rupiahGolongan = "";
                double rupiah_golongan = 0;


                if(position == 0){
                    nama_golongan = "R-1/TR";
                    va_golongan = "450 VoltAmpere";
                    rupiah_golongan = 165;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 1){
                    nama_golongan = "R-1/TR";
                    va_golongan = "900 VoltAmpere";
                    rupiah_golongan = 274;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 2){
                    nama_golongan = "R-1M/TR";
                    va_golongan = "900 VoltAmpere";
                    rupiah_golongan = 1352;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 3){
                    nama_golongan = "R-1/TR";
                    va_golongan = "1300 VoltAmpere";
                    rupiah_golongan = 1444.70;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 4){
                    nama_golongan = "R-1/TR";
                    va_golongan = "2200 VoltAmpere";
                    rupiah_golongan = 1444.70;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 5){
                    nama_golongan = "R-2/TR";
                    va_golongan = "5500 VoltAmpere";
                    rupiah_golongan = 1444.70;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }else if(position == 6) {
                    nama_golongan = "R-3/TR";
                    va_golongan = "> 5500VA VoltAmpere";
                    rupiah_golongan = 1444.70;
                    tempGolongan = parent.getItemAtPosition(position).toString();
                }
                toString_rupiahGolongan = "Rp "+String.valueOf(rupiah_golongan)+"/kWH";

                gate.setGolongan(tempGolongan);
                choosenGolongan.setText(nama_golongan);
                choosenVA.setText(va_golongan);
                choosenRupiah.setText(toString_rupiahGolongan);

                frhome_editText_totalBiaya.setText(hitungBiaya(barangs, rupiah_golongan));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        frhome_editText_totalBiaya = view.findViewById(R.id.frhome_editText_totalBiaya);
        frhome_spinerGolongan = (Spinner) view.findViewById(R.id.frhome_spinerGolongan);
        gate = new Storage();
        barangs = gate.getListBarangs();

        choosenGolongan = view.findViewById(R.id.home_choosenGolongan);
        choosenVA = view.findViewById(R.id.home_choosenVA);
        choosenRupiah = view.findViewById(R.id.home_choosenRupiah);

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
            double wattTemp = (double) (barangTemp.getWatt_barang());
            double pemakaianTemp = (double) barangTemp.getTotal_pemakaian();
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