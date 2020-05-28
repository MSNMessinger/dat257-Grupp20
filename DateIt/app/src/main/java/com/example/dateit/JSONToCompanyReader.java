package com.example.dateit;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONToCompanyReader {

    private FragmentActivity activity;

    public JSONToCompanyReader(FragmentActivity activity) {
        this.activity = activity;
    }

    /**
     * A method that returns the file contents of the company.json file as a String
     * @return A string of the contents in company.json
     */
    private String inputStreamToString() {
        InputStream inputStream = activity.getResources().openRawResource(R.raw.company);
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Read in the companies from the json-file and creates a company object for each one of them and
     * places them in a list
     * @return a list of all companies located in the companies.json file
     * @throws JSONException
     */
    public List<Company> createCompanies(){
        try {
            List<Company> list = new ArrayList<>();
            JSONObject jsonFile = new JSONObject(inputStreamToString());
            JSONObject tmp;
            for (int i = 0; i < countItems(jsonFile); i++) {
                tmp = jsonFile.getJSONObject("" + i + "");
                Company tmpComp = new Gson().fromJson(tmp.toString(), Company.class);
                list.add(tmpComp);
            }
            return list;
        } catch (JSONException e){
            return null;
        }
    }

    /**
     * Counts the number of different companies there is in the JSON-backend file from a JSONObject
     * @param obj A json-object created from the backend file
     * @return the number of companies in the json-object
     */
    private int countItems(JSONObject obj){
        int count = 0;
        for (Iterator key = obj.keys(); key.hasNext();){
            count++;
            key.next();
        }
        return count;
    }

}
