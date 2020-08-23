package com.ming.data.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * config
 * @author 
 */
@Data
public class Config implements Serializable {
    private Integer id;

    private String key;

    private String value;

    private static final long serialVersionUID = 1L;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}