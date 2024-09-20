package com.example.pathfit3.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pathfit3.R;

public class aboutUsFragment extends Fragment {
    CardView crdCerezo, crdMercado;
    LinearLayout infoCerezo, infoSupan, infoMaliwat, infoMercado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about_us, container, false);
        initializeViews(view);
        setupClickListeners();
        return view;
    }
    private void initializeViews(View view) {
        crdCerezo = view.findViewById(R.id.crdCerezo);
        crdMercado = view.findViewById(R.id.crdMercado);

        infoCerezo = view.findViewById(R.id.infoCerezo);
        infoMercado = view.findViewById(R.id.infoMercado);
    }
    private void setupClickListeners() {
        crdCerezo.setOnClickListener(v -> {toggleInfoVisibility(infoCerezo);});
        crdMercado.setOnClickListener(v -> {toggleInfoVisibility(infoMercado);});

    }

    private void toggleInfoVisibility(LinearLayout info) {
        int visibility = (info.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(info, new AutoTransition());
        info.setVisibility(visibility);
    }
}