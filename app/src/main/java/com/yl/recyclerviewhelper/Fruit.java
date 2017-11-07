package com.yl.recyclerviewhelper;

/**
 * Fruit类，测试数据的实体类
 * Created by Luke on 2017/8/10.
 */

public class Fruit {

    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
