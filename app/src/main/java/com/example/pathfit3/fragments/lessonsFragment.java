package com.example.pathfit3.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pathfit3.R;
import com.example.pathfit3.fragments.benefitsOfDanceFragment;
import com.example.pathfit3.fragments.dancePeriodFragment;
import com.example.pathfit3.fragments.natureOfdiffDancesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class lessonsFragment extends Fragment {

    private List<TextView> lessonTopics = new ArrayList<>();
    private CardView cardViewLesson1, cardViewLesson2;
    private LinearLayout lessonLayout1, lessonLayout2;
    BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);
        bottomNav = requireActivity().findViewById(R.id.nav_view);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new homeFragment());
                fragmentTransaction.commit();

                if (bottomNav != null) {
                    bottomNav.setSelectedItemId(R.id.navigation_home);
                }
            }
        });


        initializeViews(view);
        setupClickListeners();
        return view;
    }

    private void initializeViews(View view) {
        cardViewLesson1 = view.findViewById(R.id.cardViewLesson1);
        cardViewLesson2 = view.findViewById(R.id.cardViewLesson2);
        lessonLayout1 = view.findViewById(R.id.lessonLayout1);
        lessonLayout2 = view.findViewById(R.id.lessonLayout2);

        lessonTopics.add(view.findViewById(R.id.lesson1_DancePeriods));
        lessonTopics.add(view.findViewById(R.id.lesson1_BenefitOfDance));
        lessonTopics.add(view.findViewById(R.id.lesson2_natureOfDance));
    }

    private void setupClickListeners() {
        cardViewLesson1.setOnClickListener(v -> toggleLessonVisibility(lessonLayout1));
        cardViewLesson2.setOnClickListener(v -> toggleLessonVisibility(lessonLayout2));

        for (TextView textView : lessonTopics) {
            textView.setOnClickListener(this::handleLessonTopicClick);
        }
    }

    private void toggleLessonVisibility(LinearLayout lessonLayout) {
        int visibility = (lessonLayout.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
        TransitionManager.beginDelayedTransition(lessonLayout, new AutoTransition());
        lessonLayout.setVisibility(visibility);
    }

    private void handleLessonTopicClick(View view) {
        Fragment selectedFragment = null;

        if (view.getId() == R.id.lesson1_DancePeriods) {
            selectedFragment = new dancePeriodFragment();
        } else if (view.getId() == R.id.lesson1_BenefitOfDance) {
            selectedFragment = new benefitsOfDanceFragment();
        } else if (view.getId() == R.id.lesson2_natureOfDance) {
            selectedFragment = new natureOfdiffDancesFragment();
        }

        if (selectedFragment != null) {
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame_layout, selectedFragment).commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Check the orientation
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Handle landscape-specific changes
            lessonLayout1.setVisibility(View.VISIBLE);
            lessonLayout2.setVisibility(View.VISIBLE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Handle portrait-specific changes
            lessonLayout1.setVisibility(View.GONE);
            lessonLayout2.setVisibility(View.GONE);
        }
    }
}
