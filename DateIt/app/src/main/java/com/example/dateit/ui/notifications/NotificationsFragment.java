package com.example.dateit.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.dateit.Company;
import com.example.dateit.CustomAdapter;
import com.example.dateit.JSONToCompanyReader;
import com.example.dateit.MainActivity;
import com.example.dateit.R;
import com.example.dateit.ui.dashboard.DashboardFragment;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private CustomAdapter customAdapterNotes = null;
    private CustomAdapter customAdapterFavorites = null;

    private NotificationsViewModel notificationsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.titleToVisit);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        try {
            populateListFavorites(root);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        populateListNotes(root);
        return root;
    }

    /**
     * Populates the list of favorites with items marked as favorite from DB
     * @param root
     */
    private void populateListFavorites(View root) throws JSONException {
        final ListView list = (ListView)root.findViewById(R.id.listOfFavorites);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        arrayList.addAll(filterFavorites(MainActivity.getList()));
        if(!arrayList.isEmpty()) {
            customAdapterFavorites = new CustomAdapter(getActivity(), arrayList, this, R.id.action_navigation_notifications_to_companyDetails);
            list.setAdapter(customAdapterFavorites);
        }
        setListViewHeightBasedOnChildren(list);
    }

    /**
     * Populates the list of items with notes from DB
     * @param root
     */
    private void populateListNotes(View root) {
        final ListView list = (ListView)root.findViewById(R.id.listOfNotes);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        arrayList.addAll(filterNotes(MainActivity.getList()));
        if(!arrayList.isEmpty()) {
            customAdapterNotes = new CustomAdapter(getActivity(), arrayList, this, R.id.action_navigation_notifications_to_companyDetails);
            list.setAdapter(customAdapterNotes);
        }
        setListViewHeightBasedOnChildren(list);
    }

    /**
     * filters companies by is marked as favorite
     * @param companies
     * @return
     */
    private List<Company> filterFavorites(List<Company> companies) {
        List<Company> favCompanies = new ArrayList<Company>();
        for(Company c : companies) {
            if(c.isFavorite() == 1) {
                favCompanies.add(c);
            }
        }
        return favCompanies;
    }

    /**
     * filters companies by contains note
     * @param companies
     * @return
     */
    private List<Company> filterNotes(List<Company> companies) {
        List<Company> noteCompanies = new ArrayList<Company>();
        for(Company c : companies) {
            if(c.hasNote()) {
                noteCompanies.add(c);
            }
        }
        return noteCompanies;
    }

    /**
     * Dynamically sets height od listview based on nb of children
     * @param listView
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        CustomAdapter listAdapter = (CustomAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
