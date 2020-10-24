package com.postoffice.model.yandex;

public class FeatureMember {

    private GeoObject GeoObject;

    public com.postoffice.model.yandex.GeoObject getGeoObject() {
        return GeoObject;
    }

    public void setGeoObject(com.postoffice.model.yandex.GeoObject geoObject) {
        GeoObject = geoObject;
    }
}

class GeoObject {

    private Point Point;

    public com.postoffice.model.yandex.Point getPoint() {
        return Point;
    }

    public void setPoint(com.postoffice.model.yandex.Point point) {
        Point = point;
    }
}

class Point {

    private String pos;

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

}