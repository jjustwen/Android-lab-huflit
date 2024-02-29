package com.example.buoi702;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.buoi702.fragment.ContactFragment;
import com.example.buoi702.fragment.FavoriteFragment;
import com.example.buoi702.fragment.HomeFragment;
import com.example.buoi702.fragment.ImageFragment;
import com.example.buoi702.fragment.ListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private int currentFunction = R.id.nav_home; // Mặc định là HomeFragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        // khởi tạo menu
        initMenu();
    }

    void initMenu() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu Drawer");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew = null;
                if (item.getItemId() == R.id.nav_home) {
                    fmNew = new HomeFragment();


                } else if (item.getItemId() == R.id.nav_image) {
                    fmNew = new ImageFragment();

                } else if (item.getItemId() == R.id.nav_contact) {
                    fmNew = new ContactFragment();

                }
                else if (item.getItemId() == R.id.nav_list){
                    fmNew = new ListFragment();

                }
                else {
                    fmNew = new FavoriteFragment();
                }
                loadFragment(fmNew);
                return true;
            }
        });
    }


    void loadFragment(Fragment fmNew) {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}
