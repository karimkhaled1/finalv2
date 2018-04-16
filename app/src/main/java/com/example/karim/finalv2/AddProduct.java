package com.example.karim.finalv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import static com.example.karim.finalv2.products.RESULT_LOAD_IMAGE;
import static com.example.karim.finalv2.products.selectedImage;

public class AddProduct extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mDatabase;
    private EditText name;
    private EditText des;
    private EditText price;
    private Button b;
    Bitmap selectedImage;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toast.makeText(getApplicationContext(), "put product photo", Toast.LENGTH_LONG).show();
        getphoto();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        name=findViewById(R.id.ItemName);
        des=findViewById(R.id.Itemdes);
        price=findViewById(R.id.ItemPrice);
        b=findViewById(R.id.addItemButton);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItemButton:
                 if(price.getText()==null||des.getText()==null|| name.getText()==null||price.getText().toString()==""||name.getText().toString()==""||des.getText().toString()==""){
                     return;
                 }
                final String key = mDatabase.child(Tabs.user.getUid()).child("products").push().getKey();
               // Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                StorageReference riversRef = FirebaseStorage.getInstance().getReference().child("images/"+name.getText().toString()+des.getText().toString()+".jpg");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {

    selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);

    final Uri[] url = new Uri[1];

}
catch (Exception e){
    getphoto();
}
                dialog = new ProgressDialog(AddProduct.this);
                dialog.setMessage("uploaoding, please wait. ");
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = riversRef.putBytes(data);
                final String[] downloadUrl = new String[1];
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Get a URL to the uploaded content
                     downloadUrl[0] = taskSnapshot.getDownloadUrl().toString();
                    mDatabase.child(Tabs.user.getUid()).child("products").child(key).child("photourl").setValue(downloadUrl[0]);
                   dialog.dismiss();
                   startActivity(new Intent(getApplicationContext(),products.class));
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"please try again later",Toast.LENGTH_LONG).show();

                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
                product p = new product(name.getText().toString(), des.getText().toString(), Integer.parseInt(price.getText().toString()), key);
                mDatabase.child(Tabs.user.getUid()).child("products").child(key).setValue(p);
                Adapter.data.add(p);

                break;

        }
    }
    public void getphoto(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoPickerIntent, 200);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();

            try {
                selectedImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
