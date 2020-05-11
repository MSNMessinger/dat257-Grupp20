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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        JSONToCompanyReader tmp = new JSONToCompanyReader(this);
        try {
            List<Company> companies = tmp.createCompanies();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        get_json();

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
    }

    public void run(CompanyToJSONWriter test) throws IOException, JSONException {
        test.getCompanies();

    }

    public void get_json(){

        ArrayList<String> numberList = new ArrayList<>();
        String json;
        try {

            InputStream is = getAssets().open("companies.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();



            json = new String(buffer, "UTF-8");
            JSONArray jsonArr = new JSONArray(json);

            Toast.makeText(getApplicationContext(), "jeh", Toast.LENGTH_LONG).show();

           /* for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);

                if (obj.getString("id").equals("0")) {
                    numberList.add(obj.getString("name"));
                }
            }

            */

            JSONObject obj = jsonArr.getJSONObject(0);
            String name = obj.getString("name");

            Toast.makeText(getApplicationContext(), "jeh", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
