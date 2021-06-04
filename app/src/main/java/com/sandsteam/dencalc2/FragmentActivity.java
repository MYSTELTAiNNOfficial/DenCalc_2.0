package com.sandsteam.dencalc2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import AddOn.SharedPref;

public class FragmentActivity extends AppCompatActivity {

    private long mBackPressed;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private BottomNavigationView fragment_botNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initView();
        setBottomNav();
    }
    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            finish();
            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }

    private void initView(){
        fragment_botNavView = findViewById(R.id.fragment_botNavView);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_framelayout, new HomeFragment()).commit();
    }

    private void setBottomNav(){
        fragment_botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag = null;
                if (item.getItemId()==R.id.home){
                    selectedFrag = new HomeFragment();
                }else if (item.getItemId()==R.id.list_barang){
                    selectedFrag = new ListBarangFragment();
                }else if (item.getItemId()==R.id.profile){
                    selectedFrag = new ProfileFragment();
                }

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_framelayout, selectedFrag).commit();

                return true;
            }
        });
    }
}