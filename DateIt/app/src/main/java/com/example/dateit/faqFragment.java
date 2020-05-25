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
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.dateit.ui.home.HomeFragment;

import org.w3c.dom.Text;

public class faqFragment extends Fragment {
    TextView titleContainer;
    TextView textContainer;

    Button prepareBtn;
    Button MakeContactBtn;
    Button resumeBtn;
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

        prepareBtn = view.findViewById(R.id.PrepareBtn);
        MakeContactBtn = view.findViewById(R.id.MakeContactBtn);
        resumeBtn = view.findViewById(R.id.ResumeBtn);
        setToggleActive(resumeBtn);
        // prepare
        prepareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleContainer.setText(getResources().getString(R.string.Prepare));
                textContainer.setText(getResources().getString(R.string.PrepareText));
                setToggleActive(prepareBtn);
                setToggleInactive(MakeContactBtn);
                setToggleInactive(resumeBtn);
            }
        });

        // make contact
        MakeContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleContainer.setText(getResources().getString(R.string.MakeContact));
                textContainer.setText(getResources().getString(R.string.MakeContactText));
                setToggleInactive(prepareBtn);
                setToggleActive(MakeContactBtn);
                setToggleInactive(resumeBtn);
            }
        });

        // resume crap
        resumeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleContainer.setText(getResources().getString(R.string.Resume));
                textContainer.setText(getResources().getString(R.string.ResumeText));
                setToggleInactive(prepareBtn);
                setToggleInactive(MakeContactBtn);
                setToggleActive(resumeBtn);
            }
        });
    }

    private void setToggleActive(Button btn) {
        btn.setBackground(getResources().getDrawable(R.drawable.button_background_active));
    }

    private void setToggleInactive(Button btn) {
        btn.setBackground(getResources().getDrawable(R.drawable.button_background));
    }
}
