package com.example.dateit.ui.dashboard;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;
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
            Toast.makeText(getContext(), ""+id, Toast.LENGTH_LONG).show();

            for(Company c : MainActivity.getList()){
                if(((Integer) id).equals(c.getId())){
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
        TextInputEditText companyNote = (TextInputEditText) root.findViewById(R.id.AddNotesText);



        /**
         * Sets correct values for given company
         */
        name.setText(company.getName());
        description.setText(company.getDescription());
        website.setText(company.getWebsite());
        contact.setText(company.getEmail());
        employees.setText((int)company.getEmployeesWorld()+"");
        offices.setText(company.getLocations());
        companyNote.setText(company.getNote());
        if (company.isIT()==0){
            it.setText("");
        }
        if (company.isD()==0){
            d.setText("");
        }
        if (company.isE()==0){
            e.setText("");
        }
        if (company.hasSummerJob()==0){
            summer.setText("");
        }
        if (company.hasEmployment()==0){
            internship.setText("");
        }
        if (company.hasMasterThesis()==0){
            master.setText("");
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

