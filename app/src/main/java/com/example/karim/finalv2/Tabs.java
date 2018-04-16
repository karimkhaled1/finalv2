package com.example.karim.finalv2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Tabs extends AppCompatActivity {
    static FirebaseUser user;
    ViewPager mViewPager;
    static FirebaseAuth mAuth;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        mAuth = FirebaseAuth.getInstance();
        context=getApplicationContext();
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupPageAdapter(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupPageAdapter(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Signin(), "Sign in");
        adapter.addFragment(new Signup(), "Sign up");
        viewPager.setAdapter(adapter);
    }

    public static void get(int i, String email, String password, final String phone) {
        if (i == 0) {

        } else {

        }
    }

}