package com.example.pathfit3.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pathfit3.R;
import com.example.pathfit3.lessonsCardItem;
import com.example.pathfit3.lessonsViewAdapter;
import com.example.pathfit3.quiz.benefitOfDanceQuizEz;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class benefitsOfDanceFragment extends Fragment {
    RecyclerView recyclerView;
    lessonsViewAdapter cardAdapter;
    ArrayList<lessonsCardItem> cardItemList;
    String[] cardTitle;
    int[] imageResource;
    String[] description;
    String[] topic;
    SharedPreferences sharedPreferences;
    Handler handler = new Handler();
    Runnable dialogRunnable;
    boolean isDialogShown = false;
    boolean isDialogScheduled = false;
    BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_benefits_of_dance, container, false);
        bottomNav = requireActivity().findViewById(R.id.nav_view);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new lessonsFragment());
                fragmentTransaction.commit();

                if (bottomNav != null) {
                    bottomNav.setSelectedItemId(R.id.navigation_lesson);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getContext().getSharedPreferences("com.example.pathfit3.PREFS", Context.MODE_PRIVATE);

        dataInitialized();

        recyclerView = view.findViewById(R.id.recyclerBenefitsOfDance);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        cardAdapter = new lessonsViewAdapter(getContext(), cardItemList);
        recyclerView.setAdapter(cardAdapter);

        cardAdapter.setOnItemClickListener(position -> {
            markLessonAsRead(position);
            if (areAllLessonsRead()) {
                if (!isDialogShown && !isDialogScheduled) {
                    showDialog();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (areAllLessonsRead() && !isDialogShown && !isDialogScheduled) {
            showDialog();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (areAllLessonsRead() && !isDialogShown && !isDialogScheduled) {
            showDialog();
        }
    }

    private void dataInitialized() {
        cardItemList = new ArrayList<>();
        cardTitle = new String[]{
                getString(R.string.benefitsOfDance_Physical),
                getString(R.string.benefitsOfDance_mental_Emotional),
                getString(R.string.benefitsOfDance_Social),
                getString(R.string.benefitsOfDance_Cultural),
        };
        description = new String[]{
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
        };
        topic = new String[]{
                getString(R.string.benefitsOfDance_PhysicalTopic),
                getString(R.string.benefitsOfDance_mental_EmotionalTopic),
                getString(R.string.benefitsOfDance_SocialTopic),
                getString(R.string.benefitsOfDance_CulturalTopic),
        };

        imageResource = new int[]{
                R.drawable.physical,
                R.drawable.mental,
                R.drawable.social,
                R.drawable.cultural,
        };

        for (int i = 0; i < cardTitle.length; i++) {
            lessonsCardItem items = new lessonsCardItem(cardTitle[i], description[i], topic[i], imageResource[i]);
            cardItemList.add(items);
        }

        lessonReadStatus();
    }

    private void lessonReadStatus() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < cardTitle.length; i++) {
            editor.putBoolean("lesson_" + i + "_read", false);
        }
        editor.putBoolean("dialog_shown", false);
        editor.apply();
    }

    private void markLessonAsRead(int position) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("lesson_" + position + "_read", true);
        editor.apply();
    }

    private boolean areAllLessonsRead() {
        for (int i = 0; i < cardTitle.length; i++) {
            if (!sharedPreferences.getBoolean("lesson_" + i + "_read", false)) {
                return false;
            }
        }
        return true;
    }

    private void showDialog() {
        isDialogShown = true;

        new AlertDialog.Builder(getContext())
                .setTitle("Quiz Time!")
                .setMessage("You have read all the lessons. Are you ready to take the quiz?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    isDialogShown = false;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("dialog_shown", true);
                    editor.apply();

                    // Start the quiz activity
                    startActivity(new Intent(getContext(), benefitOfDanceQuizEz.class));
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    isDialogShown = false;
                    if (!isDialogScheduled) {
                        startRepetitiveDialog();
                    }
                })
                .show();
    }

    private void startRepetitiveDialog() {
        if (dialogRunnable != null) {
            handler.removeCallbacks(dialogRunnable);
        }
        isDialogScheduled = true;
        dialogRunnable = new Runnable() {
            @Override
            public void run() {
                if (isAdded() && !isDialogShown) {
                    showDialog();
                }
                // Schedule the next dialog appearance in 30 seconds
                handler.postDelayed(this, 30000);
            }
        };
        handler.postDelayed(dialogRunnable, 30000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialogRunnable != null) {
            handler.removeCallbacks(dialogRunnable);
        }
    }
}
