package com.example.dateit.ui.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dateit.Company;
import com.example.dateit.CustomAdapter;
import com.example.dateit.MainActivity;
import com.example.dateit.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private CustomAdapter customAdapterNotes = null;
    private CustomAdapter customAdapterFavorites = null;
    private ImageView heartImg;
    private ImageView arrowImg;
    private static boolean  showFavorites = true;
    private static boolean showNotes = true;
    private TextView addFavoritesText;
    private TextView addNotesText;

    private NotificationsViewModel notificationsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        addFavoritesText = root.findViewById(R.id.addFavoritesText);
        addNotesText = root.findViewById(R.id.addNoteText);

        populateListFavorites(root);
        populateListNotes(root);
        return root;
    }

    /**
     * Populates the list of favorites with items marked as favorite from DB
     * @param root
     */
    private void populateListFavorites(View root) {
        final ListView list = root.findViewById(R.id.listOfFavorites);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        arrayList.addAll(filterFavorites(MainActivity.getList()));
        if(!arrayList.isEmpty()) {
            addFavoritesText.setText("");
            customAdapterFavorites = new CustomAdapter(getActivity(), arrayList, this, R.id.action_navigation_notifications_to_companyDetails);
            list.setAdapter(customAdapterFavorites);
        }
        //makeListHide(list, !showFavorites);
        if (showFavorites) {
            setListViewHeightBasedOnChildren(list);
        } else {
            list.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Populates the list of items with notes from DB
     * @param root
     */
    private void populateListNotes(View root) {
        final ListView list = root.findViewById(R.id.listOfNotes);
        ArrayList<Company> arrayList = new ArrayList<Company>();
        arrayList.addAll(filterNotes(MainActivity.getList()));
        if(!arrayList.isEmpty()) {
            addNotesText.setText("");
            customAdapterNotes = new CustomAdapter(getActivity(), arrayList, this, R.id.action_navigation_notifications_to_companyDetails);
            list.setAdapter(customAdapterNotes);
        }
        //makeListHide(list, !showNotes);
        if(showNotes) {
            setListViewHeightBasedOnChildren(list);
        } else {
            list.setVisibility(View.INVISIBLE);
        }
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

    private int getListHeight(ListView listView) {
        CustomAdapter listAdapter = (CustomAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();

        }
        return totalHeight;
    }

        private void makeListHide(ListView listView, boolean hide){
            CustomAdapter listAdapter = (CustomAdapter) listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = getListHeight(listView);
            listView.setVisibility(View.VISIBLE);

            if(hide){
                totalHeight = 0;
                listView.setVisibility(View.INVISIBLE);
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final ListView list = view.findViewById(R.id.listOfFavorites);
            final ListView notesList = view.findViewById(R.id.listOfNotes);


            heartImg = view.findViewById(R.id.favoriteImageHeart);
            if (showFavorites) {
                heartImg.setImageResource(R.drawable.up_arrow_key);
            } else {
                heartImg.setImageResource(R.drawable.down_arrow_key);
            }
            heartImg.callOnClick();

            heartImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(showFavorites) {
                        heartImg.setImageResource(R.drawable.down_arrow_key);
                        list.setVisibility(View.INVISIBLE);
                        makeListHide(list, true);
                        showFavorites = false;
                    } else {
                        heartImg.setImageResource(R.drawable.up_arrow_key);
                        showFavorites = true;
                        list.setVisibility(View.VISIBLE);
                        setListViewHeightBasedOnChildren(list);
                    }
                }
            });

            arrowImg = view.findViewById(R.id.arrowImg);
            if(showNotes) {
                arrowImg.setImageResource(R.drawable.up_arrow_key);
            } else {
                arrowImg.setImageResource(R.drawable.down_arrow_key);
            }
            arrowImg.callOnClick();

            arrowImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(showNotes) {
                        arrowImg.setImageResource(R.drawable.down_arrow_key);
                        notesList.setVisibility(View.INVISIBLE);
                        makeListHide(notesList, true);
                        showNotes = false;
                    } else {
                        arrowImg.setImageResource(R.drawable.up_arrow_key);
                        showNotes = true;
                        notesList.setVisibility(View.VISIBLE);
                        setListViewHeightBasedOnChildren(notesList);
                    }
                }
            });


        }
    }
