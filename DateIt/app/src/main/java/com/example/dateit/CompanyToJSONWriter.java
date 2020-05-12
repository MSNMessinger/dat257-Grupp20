package com.example.dateit;

import android.os.Environment;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CompanyToJSONWriter {

    public void getCompanies() throws JSONException, IOException {
        List<Company> list = new JSONToCompanyReader().createCompanies();
        JSONObject jsonObject = new JSONObject();

        String json;

        for(Company company : list){
            JSONObject add = new JSONObject();
            add.put("name", "test");

            JSONArray arr = new JSONArray();
            arr.put(add);
        }

        Gson gson = new Gson();
        String str = gson.toJson(list.get(0).getName());





        jsonObject.put("name", list.get(0).getName());

        JSONObject company = new JSONObject();

      //  company.put("note", str);

        FileWriter writer = new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "company.json", true);
        writer.append(str);
        writer.flush();
        writer.close();

    }
}
