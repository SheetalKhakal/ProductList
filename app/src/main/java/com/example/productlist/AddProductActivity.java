package com.example.productlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText mEditTextID,mEditTextName,mEditTextBio,mEditTextPrice;
    Button mAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        myDb = new DatabaseHelper(this);

        mEditTextID = (EditText) findViewById(R.id.editTextID);
        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextBio = (EditText) findViewById(R.id.editTextBio);
        mEditTextPrice = (EditText) findViewById(R.id.editTextPrice);
        mAddButton =(Button) findViewById(R.id.addButton);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.productId = mEditTextID.getText().toString();
                product.productName = mEditTextName.getText().toString();
                product.productBio = mEditTextBio.getText().toString();
                product.productPrice = mEditTextPrice.getText().toString();

                boolean addData= myDb.insertData(product);
                Log.e("The value of my data", String.valueOf(addData));
                Toast.makeText(AddProductActivity.this,"Product details are added successfully.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}