package com.example.dateit.ui.companies;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dateit.Company;
import com.example.dateit.MainActivity;
import com.example.dateit.R;
import com.google.android.material.textfield.TextInputEditText;

public class CompanyDetailsFragment extends Fragment {

    CompanyDetailsViewModel companyDetailsViewModel;
    Company company;
    int id;
    private ImageView heartImg;
    private ImageView logo;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            id = bundle.getInt("id");
                System.out.println(bundle);
            for(Company c : MainActivity.getList()){
                if( id == c.getId()){
                    System.out.println(c);
                    company = c;
                    break;
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        companyDetailsViewModel = ViewModelProviders.of(this).get(CompanyDetailsViewModel.class);
        View root = inflater.inflate(R.layout.company_details_fragment, container, false);

        /**
         * Connects the object's text to the textview for all different headings.
         */
        TextView description = root.findViewById(R.id.AboutText);
        TextView name = root.findViewById(R.id.companyName);
        TextView it = root.findViewById(R.id.ITkeyword);
        TextView d = root.findViewById(R.id.CSkeyword);
        TextView e = root.findViewById(R.id.Elektrokeyword);
        TextView summer = root.findViewById(R.id.SummerJobID);
        TextView master = root.findViewById(R.id.MasterID);
        TextView internship = root.findViewById(R.id.JobOfferID);
        TextView website = root.findViewById(R.id.WebsiteInformation);
        TextView contact = root.findViewById(R.id.ContactInformation);
        TextView employees = root.findViewById(R.id.EmployeesInformation);
        TextView offices = root.findViewById(R.id.OfficesInformation);
        final TextInputEditText companyNote = root.findViewById(R.id.AddNotesText);

        companyNote.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    company.setNote(companyNote.getText().toString());
                }
            }
        });

        /**
         * Sets correct values for given company
         */
        name.setText(company.getName());
        description.setText(company.getDescription());
        website.setText(company.getWebsite());
        contact.setText(company.getEmail());
        employees.setText(company.getEmployeesWorld()+"");
        offices.setText(company.getLocations());
        companyNote.setText(company.getNote());
        if (company.isIT()==0){
            it.setTextColor(getResources().getColor(R.color.TextColorSuperLight));
        }
        if (company.isD()==0){
            d.setTextColor(getResources().getColor(R.color.TextColorSuperLight));
        }
        if (company.isE()==0){
            e.setTextColor(getResources().getColor(R.color.TextColorSuperLight));
        }
        if (company.hasSummerJob()==0){
            summer.setTextColor(getResources().getColor(R.color.TextColorSuperLight));
        }
        if (company.hasEmployment()==0){
            internship.setTextColor(getResources().getColor(R.color.TextColorSuperLight));
        }
        if (company.hasMasterThesis()==0){
            master.setTextColor(getResources().getColor(R.color.TextColorSuperLight));
        }



        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);

        logo = view.findViewById(R.id.imageLogo);
        int id = getResources().getIdentifier(company.getLogo(), "drawable", getContext().getPackageName());
        logo.setImageResource(id);


        heartImg = view.findViewById(R.id.heartImage);
        if (company.isFavorite() == 1) {
            heartImg.setImageResource(R.drawable.heart_logo);
        } else {
            heartImg.setImageResource(R.drawable.emptyheart);
        }

        heartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(company.isFavorite() == 1) {
                    heartImg.setImageResource(R.drawable.emptyheart);
                    company.setFavorite(0);
                } else {
                    heartImg.setImageResource(R.drawable.heart_logo);
                    company.setFavorite(1);
                }
            }
        });
    }





}

