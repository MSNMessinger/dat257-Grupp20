package com.example.dateit.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dateit.Company;
import com.example.dateit.CustomAdapter;
import com.example.dateit.JSONToCompanyReader;
import com.example.dateit.MainActivity;
import com.example.dateit.R;
import com.example.dateit.SubjectData;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    List<Company> list = MainActivity.getList();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

       populateList(root);
        return root;
    }

    private void populateList(View root) {
        final ListView alist = (ListView)root.findViewById(R.id.list);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        for (Company comp : list){
            arrayList.add(new Company(comp.getId(), comp.getName(), comp.getLogo()));
        }
        //arrayList.add(new Company(1, "Volvo", "volvo"));
        //arrayList.add(new Company(2, "Saab", "saab"));

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), arrayList);
        alist.setAdapter(customAdapter);
    }

    /**
     * Given a list of filters as strings (tags) will return a new list with only the companies that match at least one of the filters
     * @param filters a list of strings where one string is a tag that will be filtered by
     * @return a list with all companies that matches one of the filters
     */
    private List<Company> filter(List<String> filters) {
        List<Company> result = new ArrayList<>();
        for (Company comp : list) {
            boolean programmeInCommon = !Collections.disjoint(comp.getProgrammesAsList(), filters);
            boolean jobtypesInCommon = !Collections.disjoint(comp.getJobTypesAsList(), filters);
            if (programmeInCommon && jobtypesInCommon){
                result.add(comp);
            }
        }
        return result;
    }

    /**
     * A method, where given a word you are looking for, matches all words in a list and is returned
     * @param word the word you are looking for
     * @return a list of matching words from the list that was searched through
     */
    private List<Company> search(String word){
        List<Company> result = new ArrayList<>();
        for(Company comp : list){
            if(comp.getName().toLowerCase().contains(word.toLowerCase())){
                result.add(comp);
                System.out.println(comp.getName());
            }
        }

        return result;
    }


}
