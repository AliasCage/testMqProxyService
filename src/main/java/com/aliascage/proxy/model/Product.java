package com.aliascage.proxy.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    public static final byte UNIT_TYPE_KG = 1;
    public static final byte UNIT_TYPE_L = 2;
    public static final byte UNIT_TYPE_M = 3;

    private String name;
    private byte unit;
}
