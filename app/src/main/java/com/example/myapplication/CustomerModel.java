package com.example.myapplication;

public class CustomerModel {
    private int id;
    private String email;


    //Constructor
    public CustomerModel(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public CustomerModel() {
    }

    //Getter and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
