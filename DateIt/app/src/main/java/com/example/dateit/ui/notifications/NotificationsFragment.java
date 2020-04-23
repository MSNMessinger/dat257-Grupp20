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

import com.example.dateit.R;

public class NotificationsFragment extends Fragment {

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

        String[] namesOfCompaniesToVisit = {"Volvo", "Ericsson", "Brocoli","Volvo", "Ericsson", "Brocoli","Volvo", "Ericsson", "Brocoli"};
        ListView companyList = (ListView)root.findViewById(R.id.listToVisit);
        ArrayAdapter<String> companyToVisitAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,namesOfCompaniesToVisit);
        companyList.setAdapter(companyToVisitAdapter);
        getTotalHeightofListView(companyList);

        String[] namesOfFavorites = {"SKF", "Spotify"};
        ListView companyListOfFav = (ListView)root.findViewById(R.id.listOfFavorites);
        ArrayAdapter<String> favoriteCompanyAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,namesOfFavorites);
        companyListOfFav.setAdapter(favoriteCompanyAdapter);
        getTotalHeightofListView(companyListOfFav);

        String[] namesOfNotes = {"SKF", "Spotify", "Ericsson"};
        ListView companyListOfNotes = (ListView)root.findViewById(R.id.listOfNotes);
        ArrayAdapter<String> notesCompanyAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,namesOfNotes);
        companyListOfNotes.setAdapter(notesCompanyAdapter);
        getTotalHeightofListView(companyListOfNotes);
        return root;
    }

    public static void getTotalHeightofListView(ListView listView) {

        ListAdapter mAdapter = listView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
