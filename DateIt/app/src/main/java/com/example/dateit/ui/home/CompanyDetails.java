package com.example.dateit.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dateit.Company;
import com.example.dateit.R;
import com.google.android.material.textfield.TextInputEditText;

public class CompanyDetails extends Fragment {

    Company company;

    TextInputEditText companyNote;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.company_details_fragment, container, false);
    }

    /**
     * Changes if the company is favorite or not
     * @param company
     */
    public void changeIfFavorite(Company company){
        //company.setFavorite(!(company.isFavorite()));
    }


}
