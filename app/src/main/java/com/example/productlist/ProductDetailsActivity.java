package com.example.productlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView mTextId, mTextName, mTextBio, mTextPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        mTextId = (TextView) findViewById(R.id.sTextID);
        mTextName = (TextView) findViewById(R.id.sTextName);
        mTextBio = (TextView) findViewById(R.id.sTextBio);
        mTextPrice = (TextView) findViewById(R.id.sTextPrice);

        Intent intent = getIntent();
        String productId = intent.getStringExtra("Id");
        String productName = intent.getStringExtra("Name");
        String productBio = intent.getStringExtra("Bio");
        String productPrice = intent.getStringExtra("Price");

        if(productId != null) {
            mTextId.setText(productId);
        }
        if(productName != null){
            mTextName.setText(productName);
        }
        if(productBio != null){
            mTextBio.setText(productBio);
        }
        if(productPrice != null) {
            mTextPrice.setText(productPrice);
        }
    }
}