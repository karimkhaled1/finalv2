package com.example.karim.finalv2;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by elanaggar on 17/09/17.
 */

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.MyHolder> {


    private final ArrayList<product> models;

    public AllProductsAdapter(ArrayList<product> models){
        this.models=models;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allproducts,null);
        MyHolder myHolder=new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        product model= models.get(position);
        holder.title.setText(model.getName());
        StorageReference islandRef = FirebaseStorage.getInstance().getReference().child("images/island.jpg");

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
            public void onSuccess(byte[] bytes) {
                holder.image.setImageBitmap(  BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        });

       holder.price.setText(""+model.getPrice()+"");
    }

    @Override
    public int getItemCount()
    {
        return models.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        TextView title,price;
        ImageView image;
        public MyHolder(View itemView) {
            super(itemView);
             image=itemView.findViewById(R.id.imagee);
            title= itemView.findViewById(R.id.tiltlee);
            price=  itemView.findViewById(R.id.pricee);
        }
    }
}