package com.example.pathfit3;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeFragment extends Fragment {
    BottomNavigationView bottomNav;
    CardView crdLesson, crdQuiz, crdTutorial, crdAbout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        crdGetID(view);
        crdClickListenerAction();
        return view;
    }

    public void crdGetID(View view){
        crdLesson = view.findViewById(R.id.crdLesson);
        crdQuiz = view.findViewById(R.id.crdQuiz);
        crdTutorial = view.findViewById(R.id.crdTutorial);
        crdAbout = view.findViewById(R.id.crdAbout);
        bottomNav = requireActivity().findViewById(R.id.nav_view);
    }

    public void crdClickListenerAction(){
        crdLesson.setOnClickListener(v -> handleCardTopicClick(new lessonsFragment(),R.id.navigation_lesson));
        crdTutorial.setOnClickListener(v -> handleCardTopicClick(new tutorialsFragment(),R.id.navigation_tutorial));


    }

    public void handleCardTopicClick(Fragment fragment, int navItemId){

        if (getActivity() != null) {
            FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.frame_layout, fragment);
            fm.addToBackStack(null); //adds the transaction to the back stack
            fm.commit();
            bottomNav.setSelectedItemId(navItemId);
        }
    }
}
