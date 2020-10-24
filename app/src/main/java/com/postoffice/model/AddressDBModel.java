package com.postoffice.model;

import java.util.ArrayList;
import java.util.List;

public class AddressDBModel {

    String id;

    String lat;
    String lng;
    String additionalInfo;
    Integer porchNumber;

    ArrayList<OrganizationDBModel> address = new ArrayList<>();

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Integer getPorchNumber() {
        return porchNumber;
    }

    public ArrayList<OrganizationDBModel> getAddress() {
        return address;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setPorchNumber(Integer porchNumber) {
        this.porchNumber = porchNumber;
    }

    public void setAddress(ArrayList<OrganizationDBModel> address) {
        this.address = address;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
