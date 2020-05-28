package com.example.dateit.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dateit.R;

import java.net.URL;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ImageView fbImg;
    private ImageView emailImg;
    private ImageView findLocation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.faqButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_navigation_home_to_fragment_second);
            }
        });

        fbImg = view.findViewById(R.id.contactsFbInfo);
        fbImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DatE.IT/"));
                startActivity(browserIntent);
            }
        });

        emailImg = view.findViewById(R.id.emailImg);
        emailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"info@date-it.se"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.setType("message/rfc822");

                startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });

        findLocation = view.findViewById(R.id.findLocation);

        findLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.se/maps/place/Sven+Hultins+gata+8,+412+58+G%C3%B6teborg/@57.6863409,11.9750353,17z/data=!3m1!4b1!4m5!3m4!1s0x464ff30bc7830b6b:0x8cbe97369df49c8e!8m2!3d57.6863381!4d11.977224"));
                startActivity(browserIntent);

            }
        });
    }

}
