package com.postoffice.model.Tasks;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable {
    private long id;
    private String lat;
    private String lng;
    private String address;
    private Date createdAt;
    private Date updatedAt;
    private Pivot pivot;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getLat() { return lat; }
    public void setLat(String value) { this.lat = value; }

    public String getLng() { return lng; }
    public void setLng(String value) { this.lng = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date value) { this.createdAt = value; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date value) { this.updatedAt = value; }

    public Pivot getPivot() { return pivot; }
    public void setPivot(Pivot value) { this.pivot = value; }
}
