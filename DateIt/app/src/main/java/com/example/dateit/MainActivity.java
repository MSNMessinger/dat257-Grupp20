package com.example.dateit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dateit.ui.home.companyDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView companyName = (TextView) findViewById(R.id.companyName);
     //   companyName.setText("wwww");'

        Button goToCompanyBtn = (Button) findViewById(R.id.goToCompanyBtn);


     /*   goToCompanyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View companyView) {
                Navigation.findNavController(companyView).navigate(R.id.companyDetails);
            }
        });
      */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void ChangeFragment(View view){
        companyDetails fragment;

        if(view == findViewById(R.id.goToCompanyBtn)) {
            fragment = new companyDetails();
        }
    }

}
