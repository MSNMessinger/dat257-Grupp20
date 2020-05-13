package com.example.dateit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

public class Company {

    private Integer id;
    private String name;
    private String programs;
    private String jobtypes;
    private String description;
    private String locations;
    private Integer foundingYear;
    private Integer employeesWorld;
    private Integer employeesSwe;
    private String website;
    private String email;
    private String logo;
    private String note;
    private boolean favorite;

    public Company(Integer id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Company(Integer id, String name, String programs, String offers, String description, String locations, int foundingYear, int employeesWorld, int employeeSwe, String website, String email, String note, String logo, Boolean favorite) {
        this.id = id;
        this.name = name;
        this.programs = programs;
        this.jobtypes = offers;
        this.description = description;
        this.locations = locations;
        this.foundingYear = foundingYear;
        this.employeesWorld = employeesWorld;
        this.employeesSwe = employeeSwe;
        this.website = website;
        this.email = email;
        this.note = note;
        this.logo = logo;
        this.favorite = favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean hasNote(){
        if (this.getNote() != null)
            return true;
        else
            return false;
    }

    public int isIT(){
        if (programs.contains("IT")){
            return 1;
        } else {
            return 0;
        }
    }

    public int isD(){
        if (programs.contains("D")){
            return 1;
        } else {
            return 0;
        }
    }

    public int isE(){
        if (programs.contains("E")){
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isFavorite(){
        return favorite;
    }

    public String getLogo() { return logo; }

    public void setLogo(String id) { this.logo = logo; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    public String getJobtypes() {
        return jobtypes;
    }

    public void setJobtypes(String jobtypes) {
        this.jobtypes = jobtypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public int getEmployeesWorld() {
        return employeesWorld;
    }

    public void setEmployeesWorld(int employeesWorld) {
        this.employeesWorld = employeesWorld;
    }

    public int getEmployeeSwe() {
        return employeesSwe;
    }

    public void setEmployeeSwe(int employeeSwe) {
        this.employeesSwe = employeeSwe;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int hasSummerJob(){
        if (jobtypes.contains("Summer")){
            return 1;
        } else {
            return 0;
        }
    }

    public int hasMasterThesis(){
        if (jobtypes.contains("Master Thesis")){
            return 1;
        } else {
            return 0;
        }
    }
    public int hasInternship(){
        if (jobtypes.contains("Internship")){
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @return Returns a list of String where each String is a program associated with this company
     */
    public List<String> getProgrammesAsList(){
        String[] elements = programs.split(",");
        List<String> fixedLenghtList = Arrays.asList(elements);
        ArrayList<String> listOfString = new ArrayList<>(fixedLenghtList);
        return listOfString;
    }

    /**
     *
     * @return Returns a list of String where each String is a jobtype this company offers
     */
    public List<String> getJobTypesAsList(){
        String[] elements = jobtypes.split(",");
        List<String> fixedLenghtList = Arrays.asList(elements);
        ArrayList<String> listOfString = new ArrayList<>(fixedLenghtList);
        return listOfString;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", programs='" + programs + '\'' +
                ", jobtypes='" + jobtypes + '\'' +
                ", description='" + description + '\'' +
                ", locations='" + locations + '\'' +
                ", foundingYear=" + foundingYear +
                ", employeesWorld=" + employeesWorld +
                ", employeesSwe=" + employeesSwe +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", note '" + note + '\'' +
                '}';
    }
}
