package com.example.pathfit3.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pathfit3.R;
import com.example.pathfit3.lessonsCardItem;
import com.example.pathfit3.lessonsViewAdapter;

import java.util.ArrayList;

public class dancePeriodFragment extends Fragment {
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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dance_period_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getContext().getSharedPreferences("com.example.pathfit3.PREFS", Context.MODE_PRIVATE);

        dataInitialized();

        recyclerView = view.findViewById(R.id.recyclerViewLesson1);
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

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (areAllLessonsRead() && !isDialogShown && !isDialogScheduled) {
//            showDialog();
//        }
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (areAllLessonsRead() && !isDialogShown && !isDialogScheduled) {
//            showDialog();
//        }
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        if (dialogRunnable != null) {
//            handler.removeCallbacks(dialogRunnable);
//        }
//    }

    private void dataInitialized() {
        cardItemList = new ArrayList<>();
        cardTitle = new String[]{
                getString(R.string.lesson1_whatIsDance),
                getString(R.string.lesson1_PreHistoric),
                getString(R.string.lesson1_AncientEgypt),
                getString(R.string.lesson1_AncientGreek),
                getString(R.string.lesson1_AncientRome),
                getString(R.string.lesson1_CatolicChurchInEurope),
                getString(R.string.lesson1_DarknEarlyMiddleAge),
                getString(R.string.lesson1_EarlyRenaissance),
                getString(R.string.lesson1_EuropeIn15n16Century),
        };
        description = new String[]{
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
                getString(R.string.danceAs),
        };
        topic = new String[]{
                getString(R.string.DanceTopic),
                getString(R.string.prehistoric_Topic),
                getString(R.string.ancient_egypt_Topic),
                getString(R.string.ancient_greek_Topic),
                getString(R.string.ancient_rome_Topic),
                getString(R.string.catolic_church_Topic),
                getString(R.string.darkMiddleAges_Topic),
                getString(R.string.renaissance_Topic),
                getString(R.string.europe15th_Topic),
        };

        imageResource = new int[]{
                R.drawable.fill_settings,
                R.drawable.home,
                R.drawable.fill_settings,
                R.drawable.home,
                R.drawable.fill_settings,
                R.drawable.home,
                R.drawable.fill_settings,
                R.drawable.home,
                R.drawable.fill_settings,
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

        new AlertDialog.Builder(requireContext())
                .setTitle("Quiz Time!")
                .setMessage("You have read all the lessons. Are you ready to take the quiz?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    isDialogShown = false;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("dialog_shown", true);
                    editor.apply();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    isDialogShown = false;
                    if (!isDialogScheduled) {
                        scheduleRepetitiveDialog();
                    }
                })
                .show();
    }

    private void scheduleRepetitiveDialog() {
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
                handler.postDelayed(this, 5000); // 5 seconds delay
            }
        };
        handler.postDelayed(dialogRunnable, 5000); // Initial delay
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (dialogRunnable != null) {
//            handler.removeCallbacks(dialogRunnable);
//        }
//    }
}
