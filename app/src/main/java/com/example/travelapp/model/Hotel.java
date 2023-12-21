package com.example.travelapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotel {

    @SerializedName("holtels")
    List<Hotel.Data> data;

    public Hotel(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public  static  class  Data {
        @SerializedName("name")
    private String name;
        @SerializedName("_id")
    private String id;
        @SerializedName("province")

    private String province;
        @SerializedName("district")
    private String district;
        @SerializedName("description")
    private String description;
        @SerializedName("type")
    private String type;
        @SerializedName("image")
    private String image;
        @SerializedName("price")
    private float price;

        public Data(String name, String id, String province, String district, String description, String type, String image, float price) {
            this.name = name;
            this.id = id;
            this.province = province;
            this.district = district;
            this.description = description;
            this.type = type;
            this.image = image;
            this.price = price;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPrice() {
            return price + "đ";
        }

        public void setPrice(float price) {
            this.price = price;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    }
}
