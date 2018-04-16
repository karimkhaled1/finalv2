package com.example.karim.finalv2;


import android.app.FragmentManager;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {
    static Bitmap Frag;
    static String title;
    static String Des;
    static String phone;
    ArrayList<user> users;
    ListView ls;
    ArrayList<product> data;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        users = new ArrayList<>();
        users = products.items;
        data = new ArrayList<>();
        getData();
        isUserSigned();
        final ArrayList<productForlist> productForlist = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            productForlist.add(new productForlist(data.get(i).getName(), data.get(i).getDes(), data.get(i).getPrice(), data.get(i).getId()));
        }

        try {
            ls = view.findViewById(R.id.container2);
            listview allProductsAdapter = new listview(getContext(), productForlist);
            ls.setAdapter(allProductsAdapter);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                          @Override
                                          public void onItemClick(AdapterView<?> parent, View view, int i,
                                                                  long id) {
                                              title = data.get(i).getName();
                                              Log.e("karimkhaled", title);
                                              Des = data.get(i).getDes();
                                              for (int j = 0; j < users.size(); j++) {
                                                  for (int k = 0; k < users.get(i).getProducts().size(); k++) {
                                                      for (int f = 0; f < data.size(); f++) {
                                                          if (users.get(i).getProducts().get(j).getId() == data.get(f).getId()) {
                                                              phone = users.get(j).getPhone();
                                                          }
                                                      }

                                                  }
                                              }
                                              android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                                              FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                              fragmentTransaction.replace(R.id.contanier, new singleProductFraag()).addToBackStack(null);
                                              fragmentTransaction.commit();
                                          }
                                      }
            );
        } catch (Exception e) {
            Log.e("karimkhaled", "fy 7aga 5laaaaaaaaaaat");
        }
        for (int i = 0; i < data.size(); i++) {
           ;
                   StorageReference islandRef = FirebaseStorage.getInstance().getReference().child("images/"+data.get(i).getName()+data.get(i).getDes()+".jpg");

            final long ONE_MEGABYTE = 1024 * 1024*4;
            final int finalI = i;
            final int finalI1 = i;
            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    updateView(finalI1,BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    Log.e("malo","done");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("malo","failed");
                    // Handle any errors
                }
            });
        }
        return view;
    }

    public void isUserSigned() {
        if (Tabs.user == null) {
            return;
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUid() == Tabs.user.getUid()) {
                    users.remove(i);
                }
            }
        }
    }

    private void getData() {
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.get(i).getProducts().size(); j++) {
                data.add(users.get(0).getProducts().get(j));
            }

        }
    }
    private void updateView(int index,Bitmap b){
        View v = ls.getChildAt(index -
                ls.getFirstVisiblePosition());

        if(v == null)
            return;

        ImageView someText =  v.findViewById(R.id.imagee);
        someText.setImageBitmap(b);
    }
}
