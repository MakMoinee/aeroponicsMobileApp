package com.aeroponics.user.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aeroponics.user.R;
import com.aeroponics.user.activities.models.Users;
import com.aeroponics.user.activities.ui.home.HomeFragment;
import com.aeroponics.user.databinding.ActivityMainFormBinding;
import com.aeroponics.user.fragment.PlantsFragment;
import com.aeroponics.user.preference.UserPref;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainFormActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainFormBinding binding;
    DrawerLayout drawer;
    Fragment fragment;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainForm.toolbar);
        drawer = binding.drawerLayout;
        View headView = binding.navView.getHeaderView(0);
        Users users = new UserPref(MainFormActivity.this).getUsers();
        TextView txtEmail = headView.findViewById(R.id.txtEmail);
        TextView txtName = headView.findViewById(R.id.txtName);
        ImageView imgProfile = headView.findViewById(R.id.imgPicture);
        txtEmail.setText(users.getEmail());
        txtName.setText(String.format("%S %S", users.getFirstName(), users.getLastName()));
        if (users.getPictureURI() != null && users.getPictureURI() != "") {
            Uri uri = Uri.parse(users.getPictureURI());
            Picasso.get().load(uri).into(imgProfile);
        } else {
            imgProfile.setImageResource(R.mipmap.ic_launcher_round);
        }
        binding.navView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, binding.appBarMainForm.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                toggle.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                toggle.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                toggle.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                toggle.onDrawerStateChanged(newState);
            }
        });
        toggle.syncState();
        setTitle("Home");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_form, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_logout) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainFormActivity.this);
            DialogInterface.OnClickListener dListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        new UserPref(MainFormActivity.this).storeUser(new Users());
                        Toast.makeText(MainFormActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainFormActivity.this, SplashActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            };
            mBuilder.setMessage("Are you sure you want to log out?")
                    .setNegativeButton("Yes, proceed", dListener)
                    .setPositiveButton("No", dListener)
                    .setCancelable(false)
                    .show();
        } else if (item.getItemId() == R.id.nav_home) {
            setTitle("Home");
            fragment = new HomeFragment();
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.commit();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else if (item.getItemId() == R.id.nav_plants) {
            setTitle("Plants");
            fragment = new PlantsFragment(MainFormActivity.this);
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.commit();
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
}