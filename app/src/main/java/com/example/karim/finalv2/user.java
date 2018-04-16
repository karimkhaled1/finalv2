package com.example.karim.finalv2;

import java.util.ArrayList;

/**
 * Created by karim on 7/11/2017.
 */

public class user {
    String uid;
    String phone;
    ArrayList<product> products;

    public user() {
    }

    public user(String uid, String phone, ArrayList<product> products) {
        this.uid = uid;
        this.phone = phone;
        this.products = products;
    }
    public String getUid() {
        return uid;
    }

    public String getPhone() {
        return phone;
    }

   public ArrayList<product> getProducts() {
        return products;
    }


}
