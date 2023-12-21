package com.example.travelapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Booking {


    private List<Data> booking;

    public Booking(List<Data> booking) {
        this.booking = booking;
    }

    public List<Data> getBooking() {
        return booking;
    }

    public void setBooking(List<Data> booking) {
        this.booking = booking;
    }

    public class Data{


    @SerializedName("_id")
    private  String id;
    @SerializedName("userId")
    private  String userId;
    @SerializedName("hotelId")
    private  String hotelId;

    @SerializedName("fullName")
    private  String fullName;

    @SerializedName("dateFrom")
    private Date dateFrom;
    @SerializedName("dateTo")
    private Date dateTo;
    @SerializedName("note")
    private String note;
    @SerializedName("peopleCount")
    private int peopleCount;

        public Data(String id, String userId, String hotelId, String fullName, Date dateFrom, Date dateTo, String note, int peopleCount) {
            this.id = id;
            this.userId = userId;
            this.hotelId = hotelId;
            this.fullName = fullName;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.note = note;
            this.peopleCount = peopleCount;
        }

        public Data(String userId, String hotelId, String fullName, Date dateFrom, Date dateTo, String note, int peopleCount) {
            this.userId = userId;
            this.hotelId = hotelId;
            this.fullName = fullName;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.note = note;
            this.peopleCount = peopleCount;
        }

        public String getHotelId() {
            return hotelId;
        }

        public void setHotelId(String hotelId) {
            this.hotelId = hotelId;
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Date getDateFrom() {
            return dateFrom;
        }

        public void setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
        }

        public Date getDateTo() {
            return dateTo;
        }

        public void setDateTo(Date dateTo) {
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


}
