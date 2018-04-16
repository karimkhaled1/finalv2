package com.example.karim.finalv2;

import android.graphics.Bitmap;

/**
 * Created by karim on 13/11/2017.
 */

public class productForlist {
    String name;
    String des;
    int price;
    String id;
    Bitmap image;

    public productForlist(String name, String des,int price,String id) {
        this.name = name;
        this.des = des;
        this.price=price;
        this.id=id;

    }

    public productForlist() {
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getId() {
        return id;
    }
}
