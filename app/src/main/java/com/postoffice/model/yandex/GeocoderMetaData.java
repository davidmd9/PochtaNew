package com.postoffice.model.yandex;

public class GeocoderMetaData {

    private String precision;

    private String text;

    private String kind;

    private Address Address;

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public com.postoffice.model.yandex.Address getAddress() {
        return Address;
    }

    public void setAddress(com.postoffice.model.yandex.Address address) {
        Address = address;
    }
}
