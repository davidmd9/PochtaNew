package com.postoffice.model.yandex;

public class GeoObject {

    private Point Point;
    private MetaDataProperty metaDataProperty;

    public MetaDataProperty getMetaDataProperty() {
        return metaDataProperty;
    }

    public void setMetaDataProperty(MetaDataProperty metaDataProperty) {
        this.metaDataProperty = metaDataProperty;
    }

    public com.postoffice.model.yandex.Point getPoint() {
        return Point;
    }

    public void setPoint(com.postoffice.model.yandex.Point point) {
        Point = point;
    }
}

