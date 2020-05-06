package com.example.dateit;

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
    private String note;
    private boolean favorite;

    public Company(Integer id, String name, String programs, String offers, String description, String locations, int foundingYear, int employeesWorld, int employeeSwe, String website, String email, String note) {
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

    }

    private boolean isIT(){
        if (programs.contains("IT")){
            return true;
        } else {
            return false;
        }
    }

    private boolean isFavorite(){
        return favorite;
    }

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

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + name + '\'' +
                ", programs='" + programs + '\'' +
                ", offers='" + jobtypes + '\'' +
                ", description='" + description + '\'' +
                ", locations='" + locations + '\'' +
                ", foundingYear=" + foundingYear +
                ", employeesWorld=" + employeesWorld +
                ", employeeSwe=" + employeesSwe +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", note '" + note + '\'' +
                '}';
    }
}
