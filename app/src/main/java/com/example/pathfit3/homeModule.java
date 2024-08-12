package com.example.pathfit3;

import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.pathfit3.databinding.ActivityHomeModuleBinding;

public class homeModule extends AppCompatActivity {

    private ActivityHomeModuleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        EdgeToEdge.enable(this);
        //        setContentView(R.layout.activity_home_module);
        replaceFragment(new homeFragment());


        binding = ActivityHomeModuleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.navView.setOnItemSelectedListener(item -> {

            //            switch (item.getItemId()){
            //                case R.id.navigation_home:
            //            }

            if(item.getItemId() == R.id.navigation_home){
                replaceFragment(new homeFragment());
            } else if (item.getItemId() == R.id.navigation_lesson) {
                replaceFragment(new lessonsFragment());
            } else if (item.getItemId() == R.id.navigation_tutorial) {
                replaceFragment(new tutorialsFragment());
            }else{
                replaceFragment(new settingsFragment());
            }

            return true;
        });
        //
        //        BottomNavigationView navView = findViewById(R.id.nav_view);
        //        // Passing each menu ID as a set of Ids because each
        //        // menu should be considered as top level destinations.
        //        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
        //                .build();
        //        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_home_module);
        //        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //        NavigationUI.setupWithNavController(binding.navView, navController);
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}