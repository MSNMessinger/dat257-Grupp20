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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        companyNote = view.findViewById(R.id.addNoteToCompany);


//TODO - Fråga om hjälp

        String str = "";

        if(companyNote.getText() != null)
            str = companyNote.getText().toString();
        company.setNote(str);
    }


}
