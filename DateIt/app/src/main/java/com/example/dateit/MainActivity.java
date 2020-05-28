package com.example.dateit;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
import java.io.File;
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

    /**
     * A method that is run when a MainActivity object is being created
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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

    /**
     * A method that is run when the program is shut down
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStop() {
        saveNotes();
        saveFavorites();
        super.onStop();
    }

    /**
     * Initialises the companies and assigns it to an attribute
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initCompanies() {
            companies = new JSONToCompanyReader(this).createCompanies();
            Collections.sort(companies, new Comparator<Company>() {
                @Override
                public int compare(Company o1, Company o2) {
                    return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }
            });


        String result = readFile("notes");
        List<String> notes = new Gson().fromJson(result, (List.class));
        for (int i = 0; i < notes.size(); i++){
            companies.get(i).setNote(notes.get(i));
        }

        String resultF = readFile("favorites");
        List<String> favorites = new Gson().fromJson(resultF, (List.class));
        for (int i = 0; i < favorites.size(); i++){
            companies.get(i).setFavorite(Integer.parseInt(favorites.get(i)));
        }
    }

    /**
     * A method for saving the list a list of notes in an app-specific file
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveNotes(){
        String filename = "notes";
        List<String> notes = new ArrayList<>();
        for (Company tmpComp : companies){
            notes.add(tmpComp.getNote());
        }
        String fileContents = new Gson().toJson(notes);
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for saving the list a list of favorites in an app-specific file
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveFavorites(){
        String filename = "favorites";
        List<String> favorites = new ArrayList<>();
        for (Company tmpComp : companies){
            favorites.add(String.valueOf(tmpComp.getFavorite()));
        }
        String fileContents = new Gson().toJson(favorites);
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the contents from a file
     * @param filename the file to be read
     * @return the content as a String that will be converted to objects by the Gson API
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readFile(String filename) {
        if(!fileExists(filename)){
            if (filename == "favorites"){
                saveFavorites();
            } else if (filename == "notes"){
                saveNotes();
            }

        }
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

    /**
     * A method that checks if
     * @param filename the name of the file whose existence is to be checked
     * @return a boolean true if the file exists or false if it does not
     */
    private boolean fileExists(String filename) {
        File file = this.getFileStreamPath(filename);
        return file != null && file.exists();
    }

    /**
     * Returns list of all companies that all fragments use (or should use at least, :/)
     * @return
     */
    public static List<Company> getList() {
        return companies;
    }
}
