package com.example.karim.finalv2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by karim on 13/11/2017.
 */

public class listview extends ArrayAdapter<productForlist> {
    Context contex;
    TextView title,price;
    ImageView image;


    private  ArrayList<productForlist> models;
    public listview(Context context, ArrayList<productForlist> resource) {
        super(context, R.layout.listview, resource);
        contex=context;
        models=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View itemView=layoutInflater.inflate(R.layout.listview,parent,false);
            image=itemView.findViewById(R.id.imagee);
            title= itemView.findViewById(R.id.tiltlee);
            price=  itemView.findViewById(R.id.pricee);
        Log.e("karimkhaled",models.get(position).getName());
            title.setText(models.get(position).getName());
            price.setText(models.get(position).getPrice()+"");

        return itemView;
    }

    @Override
    public int getCount() {
        try {
            return models.size();
        }
        catch (Exception e){
            return 0;
        }
    }
}
