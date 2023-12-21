package com.example.travelapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    @SerializedName("restaurants")
    private List<Data> data;

    public Restaurant(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public  static  class  Data {

        @SerializedName("_id")
        private String  id ;
        @SerializedName("name")
        private String name;
        @SerializedName("address")
        private String address;


        @SerializedName("description")
        private String description;
        @SerializedName("image")

        private String image;
        @SerializedName("meals")

        private List<Meal> meals;

        public Data(String id, String name, String address, String description, String image ) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.description = description;
            this.image = image;
            this.meals = new ArrayList<>();
        }

        public Data(String id, String name, String address, String description, String image, List<Meal> meals) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.description = description;
            this.image = image;
            this.meals = meals;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<Meal> getMeals() {
            return meals;
        }

        public void setMeals(List<Meal> meals) {
            this.meals = meals;
        }
    }

}
