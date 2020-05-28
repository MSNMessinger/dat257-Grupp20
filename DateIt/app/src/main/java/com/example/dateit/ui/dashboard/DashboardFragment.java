package com.example.dateit.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dateit.Company;
import com.example.dateit.CustomAdapter;
import com.example.dateit.FilterCriterias;
import com.example.dateit.MainActivity;
import com.example.dateit.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<String> names = new ArrayList<>();
    private EditText etSearch;
    private FilterCriterias filterCriterias;

    private CustomAdapter customAdapter;
    private List<Company> list;

    private ToggleButton toggleIt;
    private ToggleButton toggleD;
    private ToggleButton toggleE;

    private ToggleButton toggleSummer;
    private ToggleButton toggleEmployment;
    private ToggleButton toggleMaster;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        list = MainActivity.getList();
       populateList(root);


        etSearch = (EditText) root.findViewById(R.id.searchField);

        toggleIt = (ToggleButton) root.findViewById(R.id.toggleIt);
        toggleD = (ToggleButton) root.findViewById(R.id.toggleD);
        toggleE = (ToggleButton) root.findViewById(R.id.toggleE);

        toggleSummer = (ToggleButton) root.findViewById(R.id.toggleSummer);
        toggleEmployment = (ToggleButton) root.findViewById(R.id.toggleEmployment);
        toggleMaster = (ToggleButton) root.findViewById(R.id.toggleMaster);

        // Add Text Change Listener to EditText
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                customAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        toggleIt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterCriterias.setIsIT(1);
                    setToggleActive(toggleIt);
                } else {
                    filterCriterias.setIsIT(0);
                    setToggleInactive(toggleIt);
                }
                customAdapter.getFilter().filter(etSearch.getText());
            }
        });
        toggleD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterCriterias.setIsD(1);
                    setToggleActive(toggleD);
                } else {
                    filterCriterias.setIsD(0);
                    setToggleInactive(toggleD);
                }
                customAdapter.getFilter().filter(etSearch.getText());
            }
        });
        toggleE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterCriterias.setIsE(1);
                    setToggleActive(toggleE);
                } else {
                    filterCriterias.setIsE(0);
                    setToggleInactive(toggleE);
                }
                customAdapter.getFilter().filter(etSearch.getText());
            }
        });
        toggleSummer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterCriterias.setIsSummerJob(1);
                    setToggleActive(toggleSummer);
                } else {
                    filterCriterias.setIsSummerJob(0);
                    setToggleInactive(toggleSummer);
                }
                customAdapter.getFilter().filter(etSearch.getText());
            }
        });
        toggleEmployment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterCriterias.setIsEmployment(1);
                    setToggleActive(toggleEmployment);
                } else {
                    filterCriterias.setIsEmployment(0);
                    setToggleInactive(toggleEmployment);
                }
                customAdapter.getFilter().filter(etSearch.getText());
            }
        });
        toggleMaster.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterCriterias.setIsMasterThesis(1);
                    setToggleActive(toggleMaster);
                } else {
                    filterCriterias.setIsMasterThesis(0);
                    setToggleInactive(toggleMaster);
                }
                customAdapter.getFilter().filter(etSearch.getText());
            }
        });

        return root;
    }

    private void setToggleActive(ToggleButton btn) {
        btn.setBackground(getResources().getDrawable(R.drawable.button_background_active));
    }

    private void setToggleInactive(ToggleButton btn) {
        btn.setBackground(getResources().getDrawable(R.drawable.button_background));
    }

    private void populateList(View root) {
        final ListView alist = (ListView)root.findViewById(R.id.list);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        arrayList.addAll(MainActivity.getList());
        filterCriterias = new FilterCriterias();
        customAdapter = new CustomAdapter(getActivity(), arrayList, this, R.id.action_navigation_dashboard_to_companyDetails, filterCriterias);
        alist.setAdapter(customAdapter);
        customAdapter.getFilter().filter("");
    }
}
