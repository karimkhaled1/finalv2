package com.example.karim.finalv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class products extends AppCompatActivity {
    public static ArrayList<user> items;
    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    private ImageButton imageButton;
   static int RESULT_LOAD_IMAGE;
   static Bitmap selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_products);
        drawer = findViewById(R.id.drawer);
        imageButton = findViewById(R.id.menubutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(Gravity.LEFT);
                } else {
                    drawer.closeDrawer(Gravity.LEFT);
                }

            }
        });
        items = getAllData();
        fragmentManager = getSupportFragmentManager();

    }

    private ArrayList<user> getAllData() {
        final ArrayList<user> users = new ArrayList<>();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String phone = postSnapshot.child("phone").getValue(String.class);
                    String uid = postSnapshot.child("uid").getValue(String.class);
                    ArrayList<product> pro = new ArrayList<>();
                    for (DataSnapshot post2 : postSnapshot.child("products").getChildren()) {
                        product p = post2.getValue(product.class);

                        pro.add(p);

                    }
                    user u = new user(uid, phone, pro);
                    users.add(u);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "check network", Toast.LENGTH_LONG).show();
            }
        });
        return users;
    }


    public void sign_in_up(View v) {
        if (Tabs.user == null) {
            startActivity(new Intent(products.this, Tabs.class));
        } else {
            Toast.makeText(getApplicationContext(), "you already signed", Toast.LENGTH_SHORT).show();
        }
    }


    public void addProduct(View view) {
        if (Tabs.user == null) {
            Toast.makeText(getApplicationContext(), "you have to sign in ma3lash", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(products.this, Tabs.class));
        } else {
            startActivity(new Intent(products.this,AddProduct.class));
        }
    }

    public void allProducts(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier, new ProductsFragment()).addToBackStack(null);
        fragmentTransaction.commit();
        drawer.closeDrawer(Gravity.LEFT);
    }

    public void MyProducts(View view) {
        if (Tabs.user == null) {
            Toast.makeText(getApplicationContext(), "you have to sign in ma3lash", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(products.this, Tabs.class));
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contanier, new MyProductFrag()).addToBackStack(null);
            fragmentTransaction.commit();
            drawer.closeDrawer(Gravity.LEFT);
        }
    }


}
