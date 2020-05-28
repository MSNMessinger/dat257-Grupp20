package com.example.dateit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    private List<Company> arrayList;
    private List<Company> mOriginalValues; // Original Values
    private LayoutInflater inflater;
    private Context context;
    private Fragment fragment;
    private FilterCriterias filterCriterias;
    private int action;

    private class ViewHolder {
        TextView textView;
        ImageView logo;
    }

    public CustomAdapter(FragmentActivity context, ArrayList<Company> arrayList, Fragment fragment, int action, FilterCriterias filterCriterias) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.fragment = fragment;
        this.action = action;
        this.filterCriterias = filterCriterias;
    }

    public CustomAdapter(FragmentActivity context, ArrayList<Company> arrayList, Fragment fragment, int action) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.fragment = fragment;
        this.action = action;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }


    /**
     * Generates each list item
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_row, null);
            holder.textView = convertView.findViewById(R.id.title);
            holder.logo = convertView.findViewById(R.id.list_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", arrayList.get(position).getId());
                NavHostFragment.findNavController(fragment).navigate(action, bundle);
            }
        });

        holder.textView.setText(arrayList.get(position).getName());
        int id = context.getResources().getIdentifier(arrayList.get(position).getLogo(), "drawable", context.getPackageName());
        holder.logo.setImageResource(id);

        return convertView;
    }

    /**
     * Returns search filter
     * @return
     */
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                arrayList = (List<Company>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<Company> FilteredArrList = new ArrayList<Company>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Company>(arrayList); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        Company data = mOriginalValues.get(i);
                        if (data.getName().toLowerCase().startsWith(constraint.toString()) && checkProgramType(data) && checkOfferType(data)) {
                            FilteredArrList.add(data);
                        }
                    }

                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    /**
     * Checks if a given company follows the job offer filter criteria
     * @param data the company to be checked
     * @return true if the company follows the criteria, false otherwise
     */
    private Boolean checkOfferType(Company data) {
        if(filterCriterias.getIsEmployment() == 0 && filterCriterias.getIsSummerJob() == 0 && filterCriterias.getIsMasterThesis() == 0) {
            return true;
        } else {
            return checkFilter(filterCriterias.getIsEmployment(), data.hasEmployment()) || checkFilter(filterCriterias.getIsSummerJob(), data.hasSummerJob()) || checkFilter(filterCriterias.getIsMasterThesis(), data.hasMasterThesis());
        }
    }

    /**
     * Checks if a given company follows the program filter criteria
     * @param data the company to be checked
     * @return true if the company follows the criteria, false otherwise
     */
    private Boolean checkProgramType(Company data) {
        if(filterCriterias.getIsD() == 0 && filterCriterias.getIsIT() == 0 && filterCriterias.getIsE() == 0) {
            return true;
        } else {
            return checkFilter(filterCriterias.getIsD(), data.isD()) || checkFilter(filterCriterias.getIsIT(), data.isIT()) || checkFilter(filterCriterias.getIsE(), data.isE());
        }
    }

    private Boolean checkFilter(int value1, int value2) {
        return value1 == 1 && value1 == value2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
