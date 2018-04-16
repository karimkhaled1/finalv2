package com.example.karim.finalv2;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class MyProductFrag extends Fragment {

     user u;

    public MyProductFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_product, container, false);
        u=new user();

        boolean signed=  isUserSigned();

            if(signed) {
                try {
                    Adapter adapter = new Adapter(u.getProducts());
                    RecyclerView recyclerView = view.findViewById(R.id.MyProductView);
                    recyclerView.setLayoutManager(new LinearLayoutManager
                            (getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(getContext(),"you have no items",Toast.LENGTH_SHORT).show();
                }
            }

        return view;
    }

    public boolean isUserSigned() {
            for(int i=0;i<products.items.size();i++){
                if(products.items.get(i).getUid().equals(Tabs.user.getUid())){
                    u=products.items.get(i);
                    return true;
                }
            }
        return false;
    }


}
