package com.example.ainabkoidairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mNaveView;
    private FrameLayout mFrameLayout;

    private FarmerFragment farmerFragment;
    private HomeFragment homeFragment;
    private StaffFragment staffFragment;
    private notifications notifications;
    public String admin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        farmerFragment = new FarmerFragment();
        homeFragment = new HomeFragment();
        staffFragment = new StaffFragment();
        notifications = new notifications();
        setFragment(homeFragment);

        mNaveView = (BottomNavigationView) findViewById(R.id.bottomnavmain);
        mFrameLayout = (FrameLayout) findViewById(R.id.mainframe);

        mNaveView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                admin = getIntent().getStringExtra("adminid");


                switch (menuItem.getItemId()){
                    case R.id.home_icon:

                        setFragment(homeFragment);
                    return true;

                    case R.id.anage_farmer_icon:

                        setFragment(farmerFragment);
                        return true;

                    case  R.id.manage_staff_icon:

                        setFragment(staffFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });

    Toolbar toolbar = (Toolbar)findViewById(R.id.abMain);
        setSupportActionBar(toolbar);
    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//
        admin = getIntent().getStringExtra("adminid");
        MenuItem admintxt = menu.findItem(R.id.idtext);
        admintxt.setTitle(admin);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();

        switch(menuItem) {
            case R.id.action_notification:
//
                setFragment(notifications);
                Toast.makeText(MainActivity.this, "Action Search", Toast.LENGTH_SHORT).show();
                break;



        }

        return super.onOptionsItemSelected(item);
    }
}
