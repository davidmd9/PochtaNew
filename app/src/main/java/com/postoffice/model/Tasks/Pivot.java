package com.postoffice.model.Tasks;

import java.io.Serializable;

public class Pivot implements Serializable {
    private long taskID;
    private long addressID;

    public long getTaskID() { return taskID; }
    public void setTaskID(long value) { this.taskID = value; }

    public long getAddressID() { return addressID; }
    public void setAddressID(long value) { this.addressID = value; }
}
