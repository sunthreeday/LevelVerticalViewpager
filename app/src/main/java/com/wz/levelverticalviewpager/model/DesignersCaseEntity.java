package com.wz.levelverticalviewpager.model;

import java.util.ArrayList;
import java.util.List;


public class DesignersCaseEntity extends BaseEntity<DesignersCaseEntity> {

    private List<DesignersCase> list = new ArrayList<>();

    public List<DesignersCase> getList() {
        return list;
    }

    public void setList(List<DesignersCase> list) {
        this.list = list;
    }

}
