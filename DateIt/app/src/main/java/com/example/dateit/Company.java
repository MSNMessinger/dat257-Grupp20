package com.example.dateit;

import java.util.List;

public class Company {

    private String companyName;
    private List<String> programs;
    private List<String> offers;
    private String description;
    private List<String> locations;
    private int foundingYear;
    private int employeesWorld;
    private int employeeSwe;
    private String website;
    private String email;

    public Company(String companyName, List<String> programs, List<String> offers, String description,
                   List<String> locations, int foundingYear, int employeesWorld, int employeeSwe,
                   String website, String email) {
        this.companyName = companyName;
        this.programs = programs;
        this.offers = offers;
        this.description = description;
        this.locations = locations;
        this.foundingYear = foundingYear;
        this.employeesWorld = employeesWorld;
        this.employeeSwe = employeeSwe;
        this.website = website;
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getPrograms() {
        return programs;
    }

    public void setPrograms(List<String> programs) {
        this.programs = programs;
    }

    public List<String> getOffers() {
        return offers;
    }

    public void setOffers(List<String> offers) {
        this.offers = offers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
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
        return employeeSwe;
    }

    public void setEmployeeSwe(int employeeSwe) {
        this.employeeSwe = employeeSwe;
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
}
