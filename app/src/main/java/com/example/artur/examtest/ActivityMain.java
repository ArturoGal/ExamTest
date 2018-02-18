package com.example.artur.examtest;

import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.Locale;

public class ActivityMain extends AppCompatActivity {
    boolean liked;
    Button pb;
    Button button;
    Button addtocart;
    RadioButton color1, color2, color3, color4;
    boolean addedToCart;
    boolean sizeSelected;
    int size;
    ScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            addedToCart = savedInstanceState.getBoolean("addedtocart");
            sizeSelected = savedInstanceState.getBoolean("sizeselected");
            liked = savedInstanceState.getBoolean("liked");
            size = savedInstanceState.getInt("size");
        }
        switch (size){
            case 1:
                pb = findViewById(R.id.size1);
                break;
            case 2:
                pb = findViewById(R.id.size2);
                break;
            case 3:
                pb = findViewById(R.id.size3);
                break;
            case 4:
                pb = findViewById(R.id.size4);
                break;
        }

        scrollview = findViewById(R.id.scrollView);
        color1 = (RadioButton) findViewById(R.id.color1);
        color2 = (RadioButton) findViewById(R.id.color2);
        color3 = (RadioButton) findViewById(R.id.color3);
        color4 = (RadioButton) findViewById(R.id.color4);
        addtocart = (Button) findViewById(R.id.button_addtoCart);

        if(addedToCart) {
            addtocart.setPressed(true);
            addtocart.setBackgroundDrawable(getResources().getDrawable(R.drawable.addtocart_pressed));
            addtocart.setTextColor(getResources().getColor(R.color.black));
            addtocart.setText("ADDED TO CART");
        }
        if(sizeSelected){
            pb.setPressed(true);
            pb.setTextColor(getResources().getColor(R.color.white));
            pb.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_pressed));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("addedtocart", addedToCart);
        outState.putBoolean("sizeselected", sizeSelected);
        outState.putBoolean("liked", liked);
        outState.putInt("size", size);
    }

    public void addToCart(View v){
        if(addedToCart){
            Toast.makeText(this, getResources().getString((R.string.toast_cart)), Toast.LENGTH_SHORT).show();
        }

        addtocart = (Button) v;
        addtocart.setPressed(true);
        addtocart.setBackgroundDrawable(getResources().getDrawable(R.drawable.addtocart_pressed));
        addtocart.setTextColor(getResources().getColor(R.color.black));
        addtocart.setText(getResources().getString(R.string.AdtC));
        addedToCart = true;
        Snackbar.make(scrollview, getResources().getString(R.string.snack_msg), Snackbar.LENGTH_INDEFINITE)
                .setAction(getResources().getString(R.string.snack_undo), new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        addtocart.setPressed(false);
                        addedToCart = false;
                        addtocart.setBackgroundDrawable(getResources().getDrawable(R.drawable.addtocart));
                        addtocart.setTextColor(getResources().getColor(R.color.white));
                        addtocart.setText(getResources().getString(R.string.AtC));
                    }
                }).show();
    }

    public void like(View v){
        liked = !liked;
        Toast.makeText(this, (liked? "+" : "-") + "1" + 	getResources().getString(R.string.like), Toast.LENGTH_SHORT).show();
    }

    public void sizeSelect(View v){
        sizeSelected = true;
        button = (Button) v;
        switch (v.getId()) {
            case R.id.size1:
                size = 1;
                break;
            case R.id.size2:
                size = 2;
                break;
            case R.id.size3:
                size = 3;
                break;
            case R.id.size4:
                size = 4;
                break;
        }
        if(pb == null) {
            pb = (Button) v;
        }
        else {
            pb.setPressed(false);
            pb.setTextColor(getResources().getColor(R.color.colorPrimary));
            pb.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
        }
        button.setPressed(true);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_pressed));
        pb = button;
    }
}
