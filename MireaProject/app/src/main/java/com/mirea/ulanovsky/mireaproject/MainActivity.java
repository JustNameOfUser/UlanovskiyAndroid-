package com.mirea.ulanovsky.mireaproject;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mirea.ulanovsky.mireaproject.ui.NewStoryFragment;
import com.mirea.ulanovsky.mireaproject.ui.SettingsFragment;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static SettingsFragment settingsFragment = new SettingsFragment();
    public static NewStoryFragment newStoryFragment = new NewStoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .replace(R.id.nav_host_fragment_content_main, newStoryFragment)
                    .addToBackStack(null)
                    .commit();
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull @NotNull View drawerView, float slideOffset) {}

            @Override
            public void onDrawerOpened(@NonNull @NotNull View drawerView) {
                MainActivity activity = MainActivity.this;
                activity.getSupportFragmentManager().beginTransaction().remove(MainActivity.settingsFragment).commit();
                activity.getSupportFragmentManager().beginTransaction().remove(MainActivity.newStoryFragment).commit();
            }

            @Override
            public void onDrawerClosed(@NonNull @NotNull View drawerView) {}

            @Override
            public void onDrawerStateChanged(int newState) {}
        };
        drawer.addDrawerListener(drawerListener);

        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_browser, R.id.nav_calculator,
                R.id.nav_player,
                R.id.nav_sensor, R.id.nav_voice_recorder, R.id.nav_camera,
                R.id.nav_stories, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void onSettingsClick(MenuItem menuItem) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.nav_host_fragment_content_main, settingsFragment)
                .addToBackStack(null)
                .commit();
    }
}