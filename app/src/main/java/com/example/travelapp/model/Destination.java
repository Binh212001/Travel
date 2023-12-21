package com.example.travelapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Destination {
    @SerializedName("destinations")
    private List<Data> data;


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
        @SerializedName("type")
        private String type;


        @SerializedName("province")
        private String province;
        @SerializedName("district")

        private String district;
        @SerializedName("description")

        private String description;
        @SerializedName("image")

        private String image;


        public Data(String id, String name, String type, String province, String district, String description, String image) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.province = province;
            this.district = district;
            this.description = description;
            this.image = image;
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
