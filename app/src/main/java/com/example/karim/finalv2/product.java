package com.example.karim.finalv2;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by karim on 7/11/2017.
 */
public class  product{
    String name;
    String des;
    int price;
    String id;


    public product(String name, String des,int price,String id) {
        this.name = name;
        this.des = des;
        this.price=price;
        this.id=id;

    }

    public product() {
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