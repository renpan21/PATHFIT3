package com.example.pathfit3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class AboutUs extends AppCompatActivity {
//    CardView crdCerezo, crdMercado;
//    LinearLayout infoCerezo, infoSupan, infoMaliwat, infoMercado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_us);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        views();
//        clickListeners();





    }

//    private void views() {
//        crdCerezo = findViewById(R.id.crdCerezo);
//        crdMercado = findViewById(R.id.crdMercado);
//        infoCerezo = findViewById(R.id.infoCerezo);
//        infoMercado = findViewById(R.id.infoMercado);
//    }
//
//    private void clickListeners() {
//        crdCerezo.setOnClickListener(v -> infoVisibility(infoCerezo));
//        crdMercado.setOnClickListener(v -> infoVisibility(infoMercado));
//    }
//
//    private void infoVisibility(LinearLayout info) {
//        int visibility = (info.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
//        TransitionManager.beginDelayedTransition(info, new AutoTransition());
//        info.setVisibility(visibility);
//    }
}