package com.org.iii.menuapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView mealText;
    private TextView priceText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mealText = (TextView) findViewById(R.id.meal);
        priceText = (TextView) findViewById(R.id.price);
        imageView = (ImageView) findViewById(R.id.img);

        Bundle bundle = getIntent().getExtras();
//        it = getIntent();
        String meal = bundle.getString("meal");
//        String meal = it.getStringExtra("meal");
        int price = bundle.getInt("price", -1);
//        int price = it.getIntExtra("price", -1);
        byte[] b = bundle.getByteArray("img");
//        byte[] b = it.getByteArrayExtra("img");
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);


        Log.v("will", meal + "::" +price);
//        Log.v("will", "img" + bitmap);

        mealText.setText(meal);
        priceText.setText(String.valueOf(price));
        imageView.setImageBitmap(bmp);
    }

    @Override
    public void finish() {
        super.finish();
    }
}


