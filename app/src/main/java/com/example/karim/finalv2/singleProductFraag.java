package com.example.karim.finalv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class singleProductFraag extends Fragment {
   TextView title,des;
   ImageView image;
   TextView phone;
    public singleProductFraag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_single_product_fraag, container, false);
        title=view.findViewById(R.id.FragTitle);
        des=view.findViewById(R.id.Fragdes);
        image=view.findViewById(R.id.FragImage);
        phone=view.findViewById(R.id.phone);
        title.setText(ProductsFragment.title);
        des.setText(ProductsFragment.Des);
        image.setImageBitmap(ProductsFragment.Frag);
        phone.append(ProductsFragment.phone);
        return view;
    }

}
