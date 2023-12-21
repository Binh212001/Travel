package com.example.travelapp.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    private String username;
    @SerializedName("firstName")

    private String firstName;
    @SerializedName("lastName")

    private String lastName;
    @SerializedName("phoneNumber")

    private String phoneNumber;
    @SerializedName("password")

    private String password;
    @SerializedName("avatar")

    private String avatar;

    public User(String username, String firstName, String lastName, String phoneNumber, String password, String avatar) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.avatar = avatar;
    }

    public User(String username, String firstName, String lastName, String phoneNumber, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }
}
