package com.aurorastudio.demo.globaldata;

public enum ItemStateEnum {
    ACTIVE(1, "ACTIVO"),
    DISCONTINUED (2, "DISCONTINUED");

    Integer id;
    String value;

    ItemStateEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer asInt(){
        return this.id;
    }

    public String getValue(){
        return this.value;
    }
}
