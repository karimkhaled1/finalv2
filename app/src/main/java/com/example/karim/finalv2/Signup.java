package com.example.karim.finalv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends Fragment implements View.OnClickListener {
    EditText password1;
    EditText password2;
    EditText email;
    EditText phone;
    Button b;
    public Signup() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup, container, false);
        email = view.findViewById(R.id.email);
        password1 = view.findViewById(R.id.password);
        password2 = view.findViewById(R.id.password2);
        phone = view.findViewById(R.id.phone);
        b=view.findViewById(R.id.signup);
        b.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup:
                String email1 = email.getText().toString().trim();
                String password3 = password1.getText().toString().trim();
                String password4 = password2.getText().toString().trim();
                final String number=phone.getText().toString().trim();
                try{
                 if (!password3.equals(password4)) {
                    Toast.makeText(getContext(), "password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else {
                        Tabs.mAuth.createUserWithEmailAndPassword(email1, password3)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Tabs.user = Tabs.mAuth.getCurrentUser();
                                            DatabaseReference database = FirebaseDatabase.getInstance().getReference("users").child(Tabs.user.getUid()).child("phone");
                                            database.setValue(number);
                                            DatabaseReference database2 = FirebaseDatabase.getInstance().getReference("users").child(Tabs.user.getUid()).child("uid");
                                            database2.setValue(Tabs.user.getUid());
                                            startActivity(new Intent(getContext(),products.class));
                                        } else {
                                            Toast.makeText(getContext(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                });
                    }
                }
                catch(Exception e){
                   Toast.makeText(getContext(),"all fields req. ma3lash",Toast.LENGTH_SHORT).show();
                }
        }
    }
}
