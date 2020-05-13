package com.example.dateit.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dateit.Company;
import com.example.dateit.R;
import com.example.dateit.ui.dashboard.DashboardFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CompanyDetailsFragment extends Fragment {

    private CompanyDetailsViewModel companyDetailsViewModel;
    Company company;
    int id;
    TextInputEditText companyNote;

    DashboardFragment dashboardFragment;
    List<Company> list = dashboardFragment.getList();



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        companyDetailsViewModel =
                ViewModelProviders.of(this).get(CompanyDetailsViewModel.class);
        View root = inflater.inflate(R.layout.company_details_fragment, container, false);

        /**
         * Connects the object's text to the textview for all different headings.
         */


        TextView description = (TextView) root.findViewById(R.id.AboutText);
        TextView name = (TextView) root.findViewById(R.id.companyName);
        TextView it = (TextView) root.findViewById(R.id.ITkeyword);
        TextView d = (TextView) root.findViewById(R.id.CSkeyword);
        TextView e = (TextView) root.findViewById(R.id.Elektrokeyword);
        TextView summer = (TextView) root.findViewById(R.id.SummerJobID);
        TextView master = (TextView) root.findViewById(R.id.MasterID);
        TextView internship = (TextView) root.findViewById(R.id.JobOfferID);
        TextView website = (TextView) root.findViewById(R.id.WebsiteInformation);
        TextView contact = (TextView) root.findViewById(R.id.ContactInformation);
        TextView employees = (TextView) root.findViewById(R.id.EmployeesInformation);
        TextView offices = (TextView) root.findViewById(R.id.OfficesInformation);

        description.setText(setDescription(id));
        name.setText(setName(id));

        /**
         * Find text written in company then save note to the given company.
         */
        TextInputEditText companyNote = (TextInputEditText) root.findViewById(R.id.AddNotesText);
        companyNote.setText(company.getNote());

        /**
         * Make sure that the right programs and offerings are visible for every company.
         */
        if (isIT(id)==0){
            it.setVisibility(View.INVISIBLE); }
        if (isD(id)==0){
            d.setVisibility(View.INVISIBLE);}
        if(isE(id)==0){
            e.setVisibility(View.INVISIBLE);
        }
        if(hasSummer(id)==0){
            summer.setVisibility(View.INVISIBLE);
        }
        if(hasMaster(id)==0){
            master.setVisibility(View.INVISIBLE);
        }
        if(hasInternship(id)==0){
            internship.setVisibility(View.INVISIBLE);
        }


        website.setText(setWebsite(id));
        contact.setText(setContact(id));
        employees.setText(setEmployees(id));
        offices.setText(setOffices(id));


        return root;
    }




    private String setDescription( int id) {
        String description = "fel";
        for (Company company : list){
            if (company.getId() == id) {
                description =company.getDescription();
            }
        }return description;
    }

    private void setNote(int id) {

        if(companyNote != null) {

            String str = companyNote.getText().toString();

            for (Company company : list) {
                if (company.getId() == id) {
                    company.setNote(str);
                }
            }
        }
    }

    private String setName(int id) {
        String name = "fel";
        for (Company company : list){
            if (company.getId() == id) {
                name =company.getName();
            }
        }return name;
    }

    private int isIT (int id){
        for (Company company : list){
            if (company.getId() == id)
                return company.isIT();
        }return 0;
    }

    private int isD (int id){
        for (Company company : list){
            if (company.getId() == id)
                return company.isD();
        }return 0;
    }

    private int isE (int id){
        for (Company company : list){
            if (company.getId() == id)
                return company.isE();
        }return 0;
    }

    private int hasSummer (int id){
        for (Company company : list){
            if (company.getId() == id)
                return company.hasSummerJob();
        }return 0;
    }

    private int hasMaster (int id){
        for (Company company : list){
            if (company.getId() == id)
                return company.hasMasterThesis();
        }return 0;
    }

    private int hasInternship (int id){
        for (Company company : list){
            if (company.getId() == id)
                return company.hasInternship();
        }return 0;
    }

    private String setWebsite( int id) {
        String website = "fel";
        for (Company company : list){
            if (company.getId() == id) {
                website =company.getWebsite();
            }
        }return website;
    }

    private String setContact( int id) {
        String contact = "fel";
        for (Company company : list){
            if (company.getId() == id) {
                contact =company.getEmail();
            }
        }return contact;
    }

    private String setEmployees( int id) {
        int employees = 0;
        for (Company company : list){
            if (company.getId() == id) {
                employees =company.getEmployeesWorld();
            }
        }
        String numberOfEmployees = ""+employees + "";
        return numberOfEmployees;
    }

    private String setOffices( int id) {
        String offices = "fel";
        for (Company company : list){
            if (company.getId() == id) {
                offices =company.getLocations();
            }
        } return offices;
    }
}
