package com.qijianguo.springcloud.resttemplate.bean;

import java.io.Serializable;

public class HouseInfo implements Serializable {

    private Long id;

    private String address;

    public HouseInfo() {
    }

    public HouseInfo(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
