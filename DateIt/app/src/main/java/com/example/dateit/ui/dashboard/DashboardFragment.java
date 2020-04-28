package com.example.dateit.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dateit.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //String[] namesOfCompanies = {"Volvo", "SKF", "Academic Work", "Volvo", "SKF", "Academic Work", "Volvo", "SKF", "Academic Work", "Volvo", "SKF", "Academic Work", "Volvo", "SKF", "Academic Work"};
        //ListView companyList = (ListView)root.findViewById(R.id.CompanyList);
        //ArrayAdapter<String> companyAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,namesOfCompanies);
        //companyList.setAdapter(companyAdapter);

        return root;
    }
}
