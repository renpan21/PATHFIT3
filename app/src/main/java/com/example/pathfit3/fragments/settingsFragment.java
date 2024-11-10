package com.example.pathfit3.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.pathfit3.R;
import com.example.pathfit3.musicPlayer;
import com.example.pathfit3.startupPanel;
import androidx.fragment.app.FragmentTransaction;
import androidx.activity.OnBackPressedCallback;
import com.example.pathfit3.fragments.homeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class settingsFragment extends Fragment {
    private musicPlayer mService;
    private boolean mBound = false;
    private SeekBar volBar;
    private Spinner language;
    BottomNavigationView bottomNav;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            musicPlayer.ServiceBinder binder = (musicPlayer.ServiceBinder) service;
            mService = binder.getService();
            mBound = true;

            // Set initial volume 100
            if (mService != null) {
                float currentVolume = mService.getVolume();
                int volumeProgress = (int) (currentVolume * 100);
                volBar.setProgress(volumeProgress);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(requireActivity(), musicPlayer.class);
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new homeFragment());
                fragmentTransaction.commit();

                if (bottomNav != null) {
                    bottomNav.setSelectedItemId(R.id.navigation_home);
                } else {

                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        bottomNav = requireActivity().findViewById(R.id.nav_view);
        volBar = view.findViewById(R.id.volumeSeekBar);
        language = view.findViewById(R.id.spinner);

        setupLanguageSpinner();

        // Volume SeekBar
        volBar.setMax(100);
        volBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mBound) {
                    float volume = progress / 100f;
                    mService.setVolume(volume, volume);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        return view;
    }

    private void setupLanguageSpinner() {
        String[] languages = {"English", "Tagalog"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        language.setAdapter(adapter);


        SharedPreferences prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String savedLanguage = prefs.getString("language", "en"); // Def English
        language.setSelection(savedLanguage.equals("tl") ? 1 : 0);

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguage = position == 0 ? "en" : "tl"; // English or Tagalog
                String currentLanguage = prefs.getString("language", "en");


                if (!selectedLanguage.equals(currentLanguage)) {
                    setLocale(selectedLanguage);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void setLocale(String localeCode) {
        SharedPreferences prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        prefs.edit().putString("language", localeCode).apply();

        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);

        Configuration config = requireActivity().getResources().getConfiguration();
        config.setLocale(locale);


        requireActivity().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        // need pumunta sa startup kasi hindi sasama yung logic ng musicPlayer at Base Activity pag nag refresh yung app para i translate yung mga text
        // pag tinanggal eto hindi mag i-istop yung music kahit nasa background na sya or naka exit na yung app
        Intent intent = new Intent(requireActivity(), startupPanel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish();  // Close the settings fragment/activity
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBound) {
            requireActivity().unbindService(connection);
            mBound = false;
        }
        // Reset the language preference to English when exiting the app
//        SharedPreferences prefs = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
//        prefs.edit().putString("language", "en").apply(); // Set language to English

        // Optionally stop the service if needed
//        Intent intent = new Intent(requireActivity(), musicPlayer.class);
//        requireActivity().stopService(intent);
    }
}
