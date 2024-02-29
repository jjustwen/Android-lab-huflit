package com.example.tuan04;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fragment.HomeFragment;
import com.fragment.InfoFragment;
import com.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView mnBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnBottom = findViewById(R.id.navMenu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Load Fragment lên
        loadFragment(new HomeFragment());
        mnBottom.setOnItemSelectedListener(getListener());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return true;
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener()
    {
        return new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                int idItem = item.getItemId();

                if (idItem == R.id.mnHome)
                {
                    getSupportActionBar().setTitle(item.getTitle());
                    loadFragment(new HomeFragment());
                    return true;
                } else if (idItem == R.id.mnInfo)
                {
                    getSupportActionBar().setTitle(item.getTitle());
                    loadFragment(new InfoFragment());
                    return true;
                } else if (idItem == R.id.mnSetting)
                {
                    getSupportActionBar().setTitle(item.getTitle());
                    loadFragment(new SettingFragment());
                    return true;
                }
                return true;
            }
        };
    }

    //Hàm load Fragment
    void loadFragment(Fragment fmnew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmnew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }

}