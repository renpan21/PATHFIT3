package com.example.pathfit3.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pathfit3.R;
import com.example.pathfit3.animDance.animBallroom;
import com.example.pathfit3.animDance.animCheerDance;
import com.example.pathfit3.animDance.animFolk;
import com.example.pathfit3.animDance.animHipHop;
import com.example.pathfit3.animDance.animSinulog;

public class tutorialsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorials, container, false);
        setupCardListeners(view);
        return view;
    }

    private void setupCardListeners(View view) {
        final String[] arrDances = {
                "Ballroom", "Cheer Dance", "Hip Hop", "Sinulog",
                "Folk Dance"
        };

        final int[] cardViewIds = {
                R.id.ballRoom, R.id.cheerDance, R.id.hipHop, R.id.sinulog,
                R.id.folkDance
        };

        for (int i = 0; i < cardViewIds.length; i++) {
            final String dances = arrDances[i];
            view.findViewById(cardViewIds[i]).setOnClickListener(v -> crdClick(dances));
        }
    }

    private void crdClick(String dances) {
        Intent intent;

        if (dances.equals("Ballroom")) {
            intent = new Intent(getActivity(), animBallroom.class);
        } else if (dances.equals("Cheer Dance")) {
            intent = new Intent(getActivity(), animCheerDance.class);
        } else if (dances.equals("Hip Hop")) {
            intent = new Intent(getActivity(), animHipHop.class);
        } else if (dances.equals("Sinulog")) {
            intent = new Intent(getActivity(), animSinulog.class);
        } else if (dances.equals("Folk Dance")) {
            intent = new Intent(getActivity(), animFolk.class);
        } else {
            throw new IllegalArgumentException("Unknown dance: " + dances);
        }

        startActivity(intent);
    }
}
