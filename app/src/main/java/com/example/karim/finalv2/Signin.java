package com.example.karim.finalv2;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Signin extends Fragment implements View.OnClickListener {

    EditText email;
    EditText password;
    Button b;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signin, container, false);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        b=view.findViewById(R.id.signin);
        b.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin:
                try {
                    Tabs.mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Tabs.user = Tabs.mAuth.getCurrentUser();
                                        startActivity(new Intent(getContext(),products.class));
                                    } else {
                                        Toast.makeText(getContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    // Tabs.get(0,email.getText().toString(),password.getText().toString(),"x");
                }
                catch (Exception e){
                    Toast.makeText(getContext(),"must enter both ma3lash",Toast.LENGTH_SHORT).show();
                }
        }
    }
}