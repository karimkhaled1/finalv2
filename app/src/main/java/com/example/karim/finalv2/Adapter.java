package com.example.karim.finalv2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by elanaggar on 17/09/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {

//add images
    static  ArrayList<product> data=new ArrayList<>();
    ImageButton imageButton;
    public Adapter(ArrayList<product> data){
        this.data=data;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,null);
        MyHolder myHolder=new MyHolder(view);
        Log.e("Sadfasfasdf","SDAfdsfasdfasdf");
        return myHolder;

    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
         final product x= data.get(position);
        Log.e("Sadfasfasdf","SDAfdsfasdfasdf");
        holder.title.setText(x.getName());
        holder.price.setText(x.getDes());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference[] database = {FirebaseDatabase.getInstance().getReference("users").child(Tabs.user.getUid()).child("products")};
                database[0].addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                           for(DataSnapshot post:dataSnapshot.getChildren()){
                               product product=post.getValue(product.class);
                               if(product.getName().equals(x.getName())&&product.getDes().equals(x.getDes())&&product.getPrice()==x.getPrice()){
                                   database[0] =post.getRef();
                                   database[0].setValue(null);
                                   data.remove(position);
                                   notifyItemRemoved(position);

                               }
                           }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        TextView title,price;
        public MyHolder(View itemView) {
            super(itemView);
            title=  itemView.findViewById(R.id.productTilte);
            price= itemView.findViewById(R.id.ProductPrice);
            imageButton=itemView.findViewById(R.id.imagebutton);

        }
    }
}