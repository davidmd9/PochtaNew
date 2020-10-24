package com.postoffice.model.yandex;

import java.util.List;

public class GeoObjectCollection {

    private List<FeatureMember> featureMember = null;

    public List<FeatureMember> getFeatureMember() {
        return featureMember;
    }

    public void setFeatureMember(List<FeatureMember> featureMember) {
        this.featureMember = featureMember;
    }
}
