package com.example.dateit.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dateit.Company;
import com.example.dateit.CustomAdapter;
import com.example.dateit.JSONToCompanyReader;
import com.example.dateit.R;
import com.example.dateit.SubjectData;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<Company> list = new ArrayList<>();
    private List<String> names = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        try {
            init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


       populateList(root);

        return root;
    }

    private void populateList(View root) {
        final ListView list = (ListView)root.findViewById(R.id.list);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        arrayList.add(new Company(1, "Volvo", "volvo"));
        arrayList.add(new Company(2, "Saab", "saab"));

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), arrayList);
        list.setAdapter(customAdapter);
    }

    private void init() throws JSONException {
        JSONToCompanyReader reader = new JSONToCompanyReader(getActivity());
        list = reader.createCompanies();
        for (Company comp : list){
            names.add(comp.getName());
        }
    }
}
