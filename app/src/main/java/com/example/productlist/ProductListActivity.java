package com.example.productlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    ArrayList<Product> mProductList= new ArrayList();
    Button mAddButton;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        myDb = new DatabaseHelper(this);
        mAddButton = (Button) findViewById(R.id.addButton);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this,AddProductActivity.class);
                startActivity(intent);
            }
        });

     }

    @Override
    protected void onResume() {
        super.onResume();
        mProductList= myDb.getProductList();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ProductListItemAdapter adapter = new ProductListItemAdapter(mProductList,myDb,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}