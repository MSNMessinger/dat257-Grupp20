package com.example.dateit.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dateit.Company;
import com.example.dateit.CustomAdapter;
import com.example.dateit.JSONToCompanyReader;
import com.example.dateit.MainActivity;
import com.example.dateit.R;
import com.example.dateit.SubjectData;
import com.example.dateit.ui.home.HomeFragment;

import org.json.JSONException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<Company> list = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private EditText etSearch;

    private CustomAdapter customAdapter;
    List<Company> list = MainActivity.getList();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

       populateList(root);


        etSearch = (EditText) root.findViewById(R.id.searchField);
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


        return root;
    }

    private void populateList(View root) {
        final ListView alist = (ListView)root.findViewById(R.id.list);
        ArrayList<Company> arrayList = new ArrayList<Company>();

        arrayList.addAll(MainActivity.getList());

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), arrayList);
        alist.setAdapter(customAdapter);
    }

    /**
     * Initializes the list in the company fragment page
     * @throws JSONException
     */
    private void init() throws JSONException {
        JSONToCompanyReader reader = new JSONToCompanyReader(getActivity());
        list = reader.createCompanies();
        for (Company comp : list) {
            names.add(comp.getName());
        }
    }

    public List<Company> getList() {
        return list;
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
