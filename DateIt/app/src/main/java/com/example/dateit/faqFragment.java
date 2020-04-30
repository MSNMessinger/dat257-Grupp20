package com.example.dateit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dateit.ui.home.HomeFragment;

import org.w3c.dom.Text;

public class faqFragment extends Fragment {
    TextView titleContainer;
    TextView textContainer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faq, container, false);

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleContainer = view.findViewById(R.id.titleContainer);
        textContainer = view.findViewById(R.id.contentContainer);

        // prepare
        view.findViewById(R.id.PrepareBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleContainer.setText(getResources().getString(R.string.Prepare));
                textContainer.setText(getResources().getString(R.string.PrepareText));
            }
        });

        // make contact
        view.findViewById(R.id.MakeContactBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleContainer.setText(getResources().getString(R.string.MakeContact));
                textContainer.setText(getResources().getString(R.string.MakeContactText));
            }
        });

        // resume crap
        view.findViewById(R.id.ResumeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleContainer.setText(getResources().getString(R.string.Resume));
                textContainer.setText(getResources().getString(R.string.ResumeText));
            }
        });

        // NavHostFragment.findNavController(View);

        //view.findViewById(R.id.faqButton).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment_faq, null));
    }

 /*
    public void changeFaqFragment(View view) {
       Fragment fragment;

        if (view == view.findViewById(R.id.PrepareBtn)) {
            fragment = new PrepareFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();
        }
        if (view == view.findViewById(R.id.ResumeBtn)) {
            fragment = new Resume();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();
        }
        if (view == view.findViewById(R.id.MakeContactBtn)){
            fragment = new MakeContact();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();
        }
    }*/
}
