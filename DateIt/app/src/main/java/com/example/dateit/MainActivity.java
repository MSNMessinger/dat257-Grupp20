package com.example.dateit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static List<Company>  list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TextView companyName = (TextView) findViewById(R.id.companyName);

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

        try {
            init();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the list in the company fragment page
     * @throws JSONException
     */
    private void init() throws JSONException {
        JSONToCompanyReader reader = new JSONToCompanyReader(this);
        list = reader.createCompanies();
    }

    /**
     * Returns list of all companies that all fragments use (or should use at least, :/)
     * @return
     */
    public static List<Company> getList() {
        return list;
    }
}
