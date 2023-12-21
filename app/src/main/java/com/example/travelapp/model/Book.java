package com.example.travelapp.model;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("_id")
    private  String id;
    @SerializedName("userId")
    private  String userId;
    @SerializedName("hotelId")
    private  String hotelId;

    @SerializedName("fullName")
    private  String fullName;

    @SerializedName("dateFrom")
    private String dateFrom;
    @SerializedName("dateTo")
    private String dateTo;
    @SerializedName("note")
    private String note;
    @SerializedName("peopleCount")
    private int peopleCount;

    public Book(String id, String userId, String hotelId, String fullName, String dateFrom, String dateTo, String note, int peopleCount) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
        this.peopleCount = peopleCount;
    }

    public Book(String userId, String hotelId, String fullName, String dateFrom, String dateTo, int peopleCount) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
        this.peopleCount = peopleCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}

