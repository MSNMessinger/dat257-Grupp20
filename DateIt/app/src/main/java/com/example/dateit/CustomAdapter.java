package com.example.dateit;

import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dateit.ui.dashboard.CompanyDetailsFragment;
import com.example.dateit.ui.dashboard.DashboardFragment;
import com.example.dateit.ui.home.HomeFragment;

import java.util.ArrayList;

public class CustomAdapter implements ListAdapter {
    ArrayList<Company> arrayList;
    FragmentActivity context;
    Fragment fragment;


    public CustomAdapter(FragmentActivity context, ArrayList<Company> arrayList, Fragment fragment) {
        this.arrayList=arrayList;
        this.context=context;
        this.mContext = context;
        this.fragment = fragment;

    }
    private FragmentActivity mContext;
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
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
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Company company = arrayList.get(position);
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.list_row, null);
            final View finalConvertView = convertView;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                   // finalConvertView.setVisibility(View.INVISIBLE);

                    Bundle bundle = new Bundle();
                    bundle.putInt("id", company.getId());

                  //  System.out.println(bundle);

                    NavHostFragment.findNavController(fragment).navigate(R.id.action_navigation_dashboard_to_companyDetails, bundle);

                }
            });
            TextView title=convertView.findViewById(R.id.title);
            ImageView img=convertView.findViewById(R.id.list_image);
            title.setText(company.getName());

            int id = context.getResources().getIdentifier(company.getLogo(), "drawable", context.getPackageName());
            Drawable drawable = context.getResources().getDrawable(id);
            img.setImageResource(id);
        }
        return convertView;
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
