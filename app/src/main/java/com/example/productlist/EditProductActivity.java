package com.example.productlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProductActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText emEditTextID,emEditTextName,emEditTextBio,emEditTextPrice;
    Button mEditButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        myDb = new DatabaseHelper(this);
        emEditTextID = (EditText) findViewById(R.id.editTextID);
        emEditTextName = (EditText) findViewById(R.id.editTextName);
        emEditTextBio = (EditText) findViewById(R.id.editTextBio);
        emEditTextPrice = (EditText) findViewById(R.id.editTextPrice);
        mEditButton = (Button) findViewById(R.id.editButton);

        Intent intent = getIntent();
        String productId = intent.getStringExtra("Id");
        String productName = intent.getStringExtra("Name");
        String productBio = intent.getStringExtra("Bio");
        String productPrice = intent.getStringExtra("Price");

        if(productId != null) {
            emEditTextID.setText(productId);
        }
        if(productName != null){
        emEditTextName.setText(productName);
        }
        if(productBio != null){
        emEditTextBio.setText(productBio);
        }
        if(productPrice != null) {
        emEditTextPrice.setText(productPrice);
        }


        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.productId = emEditTextID.getText().toString();
                product.productName = emEditTextName.getText().toString();
                product.productBio = emEditTextBio.getText().toString();
                product.productPrice = emEditTextPrice.getText().toString();

                myDb.editData(product);
                Toast.makeText(EditProductActivity.this, "Product details are edited successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}