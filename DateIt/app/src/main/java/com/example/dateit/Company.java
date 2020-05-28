package com.example.dateit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Company {

    private int id;
    private String name;
    private String programs;
    private String jobtypes;
    private String description;
    private String locations;
    private int foundingYear;
    private int employeesWorld;
    private int employeesSwe;
    private String website;
    private String email;
    private String logo;
    private String note;
    private int favorite;

    public Company(int id, String name, String programs, String jobtypes, String description, String locations, int foundingYear, int employeesWorld, int employeesSwe, String website, String email, String logo, String note, int favorite) {
        this.id = id;
        this.name = name;
        this.programs = programs;
        this.jobtypes = jobtypes;
        this.description = description;
        this.locations = locations;
        this.foundingYear = foundingYear;
        this.employeesWorld = employeesWorld;
        this.employeesSwe = employeesSwe;
        this.website = website;
        this.email = email;
        this.logo = logo;
        this.note = note;
        this.favorite = favorite;
    }


    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public boolean hasNote(){
        return !note.isEmpty();
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

    public int getFavorite() {
        return favorite;
    }

    public int isFavorite(){
        return favorite;
    }

    public String getLogo() { return logo; }

    public void setLogo(String id) { this.logo = logo; }

    public int getId() { return id; }

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

    public int getEmployeesSwe() {
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
        if (jobtypes.contains("Master thesis")){
            return 1;
        } else {
            return 0;
        }
    }
    public int hasEmployment(){
        if (jobtypes.contains("Employment")){
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
