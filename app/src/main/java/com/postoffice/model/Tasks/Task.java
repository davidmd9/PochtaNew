package com.postoffice.model.Tasks;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Task implements Serializable {
    private long id;
    private String title;
    private String text;
    private String bonus;
    private long status;
    private Date createdAt;
    private Date updatedAt;
    private List<Address> addresses;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getText() { return text; }
    public void setText(String value) { this.text = value; }

    public String getBonus() { return bonus; }
    public void setBonus(String value) { this.bonus = value; }

    public long getStatus() { return status; }
    public void setStatus(long value) { this.status = value; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date value) { this.createdAt = value; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date value) { this.updatedAt = value; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> value) { this.addresses = value; }
}
