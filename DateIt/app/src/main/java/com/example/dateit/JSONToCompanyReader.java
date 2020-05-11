package com.example.dateit;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONToCompanyReader {

    private FragmentActivity activity;
    private String jsonString;

    public JSONToCompanyReader(FragmentActivity activity) {
        this.activity = activity;
        this.jsonString = inputStreamToString();
    }

    public JSONToCompanyReader() {

    }

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

    public List<Company> createCompanies() throws JSONException {
        List<Company> list = new ArrayList<>();
        JSONObject jsonFile = new JSONObject(jsonString);
        JSONObject tmp;
        for (int i = 0; i < countItems(jsonFile); i++) {
            tmp = jsonFile.getJSONObject("" + i + "");
            Company tmpComp = new Gson().fromJson(tmp.toString(), Company.class);
            list.add(tmpComp);
        }
        return list;
    }

    /**
     * Create a list of companies marked as favorite.
     * @return
     * @throws JSONException
     */
    public List<Company> getFavorites() throws JSONException {
        List<Company> list = new ArrayList<>();
        JSONObject jsonFile = new JSONObject(jsonString);
        JSONObject tmp = new JSONObject();
        for (int i = 0; i < countItems(jsonFile); i++) {
                tmp = jsonFile.getJSONObject("" + i + "");
                Company tmpComp = new Gson().fromJson(tmp.toString(), Company.class);
                if(tmpComp.isFavorite()) {
                    list.add(tmpComp);
                }
        }
        return list;
    }

    /**
     * make a list of companies which have a note.
     * @return
     * @throws JSONException
     */
    public List<Company> hasNote() throws JSONException {
        List<Company> list = new ArrayList<>();
        JSONObject jsonFile = new JSONObject(jsonString);
        JSONObject tmp = new JSONObject();
        for (int i = 0; i < countItems(jsonFile); i++) {
            if(getCompany(i).hasNote()){
                tmp = jsonFile.getJSONObject("" + i + "");
                Company tmpComp = new Gson().fromJson(tmp.toString(), Company.class);
                list.add(tmpComp);
            }
        }
        return list;
    }



    public Company getCompany(int id) throws JSONException {
        JSONObject jsonFile = new JSONObject(jsonString);
        JSONObject tmp = jsonFile.getJSONObject("" + id + "");
        return new Gson().fromJson(tmp.toString(), Company.class);
    }

    private int countItems(JSONObject obj){
        int count = 0;
        for (Iterator key = obj.keys(); key.hasNext();){
            count++;
            key.next();
        }
        return count;
    }


}
