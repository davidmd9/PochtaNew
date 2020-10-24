package com.postoffice.model;

public class OrganizationDBModel {
    String organizationName;
    String workFrom;
    String workTo;

    public String getOrganizationName() {
        return organizationName;
    }

    public String getWorkFrom() {
        return workFrom;
    }

    public String getWorkTo() {
        return workTo;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setWorkFrom(String workFrom) {
        this.workFrom = workFrom;
    }

    public void setWorkTo(String workTo) {
        this.workTo = workTo;
    }
}
