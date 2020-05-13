package com.example.dateit;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static List<Company> companies;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        initCompanies();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStop() {
        saveNotes();
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initCompanies(){
        try {
            companies = new JSONToCompanyReader(this).createCompanies();
            Collections.sort(companies, new Comparator<Company>() {
                @Override
                public int compare(Company o1, Company o2) {
                    return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String result = readNotes("notes");
        List<String> notes = new Gson().fromJson(result, (List.class));
        for (int i = 0; i < JSONToCompanyReader.nrOfCompanies; i++){
            companies.get(i).setNote(notes.get(i));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveNotes(){
        String filename = "notes";
        List<String> notes = new ArrayList<>();
        for (Company tmpComp : companies){
            notes.add(tmpComp.getNote());
        }
        //resetNotes(notes);
        String fileContents = new Gson().toJson(notes);
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetNotes(List<String> notes){
        for (Company tmpComp : companies){
            notes.add(tmpComp.getNote());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readNotes(String filename){
        String contents = null;
        FileInputStream fis = null;
        try {
            fis = this.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
             contents = stringBuilder.toString();
        }
        return contents;
    }

    private void testSaveFunction(){
        for (Company company : companies){
            System.out.println("name: " + company.getName() + "& note: " + company.getNote());
        }
    }


}
