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
     * Gets text from input and saves that text to the note of the corresponding company.
     * @param view
     * @param savedInstanceState
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        companyNote = view.findViewById(R.id.addNoteToCompany);
        String str = "";

        if(companyNote.getText() != null)
            str = companyNote.getText().toString();
        company.setNote(str);
    }

    /**
     * Changes if the company is favorite or not
     * @param company
     */
    public void changeIfFavorite(Company company){
        company.setFavorite(!(company.isFavorite()));
    }


}
