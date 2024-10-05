package com.example.pathfit3.fragments;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pathfit3.BaseActivity;
import com.example.pathfit3.R;
import com.example.pathfit3.fragments.benefitsOfDanceFragment;
import com.example.pathfit3.fragments.dancePeriodFragment;
import com.example.pathfit3.fragments.natureOfdiffDancesFragment;

import java.util.ArrayList;
import java.util.List;

public class lessonsFragment extends Fragment {

    private List<TextView> lessonTopics = new ArrayList<>();
    private CardView cardViewLesson1, cardViewLesson2;
    private LinearLayout lessonLayout1, lessonLayout2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);

        initializeViews(view);
        setupClickListeners();
//        playMusic();
        return view;

    }
//    private void playMusic() {
//        if (getActivity() instanceof BaseActivity) {
//            ((BaseActivity) getActivity()).playMusic();
//        }
//    }

    private void initializeViews(View view) {
        cardViewLesson1 = view.findViewById(R.id.cardViewLesson1);
        cardViewLesson2 = view.findViewById(R.id.cardViewLesson2);
        lessonLayout1 = view.findViewById(R.id.lessonLayout1);
        lessonLayout2 = view.findViewById(R.id.lessonLayout2);

        // Add all lesson topic TextViews to the list
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
        if (view.getId() == R.id.lesson1_DancePeriods) {
            Fragment DancePeriodsFrag = new dancePeriodFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame_layout,DancePeriodsFrag).commit();
        } else if (view.getId() == R.id.lesson1_BenefitOfDance) {
            Fragment benefitOfDanceFrag = new benefitsOfDanceFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame_layout,benefitOfDanceFrag).commit();
        }else{
            Fragment natureOfDanceFrag = new natureOfdiffDancesFragment();
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame_layout,natureOfDanceFrag).commit();
        }
    }


}
