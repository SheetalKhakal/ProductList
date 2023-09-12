package com.example.productlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME= "Product.db";
    public static final String TABLE_NAME= "prod_table";
    public static final String COL_1= "ID";
    public static final String COL_2= "NAME";
    public static final String COL_3= "BIO";
    public static final String COL_4= "PRICE";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID TEXT PRIMARY KEY,NAME TEXT, BIO TEXT, PRICE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Product product)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,product.productId);
        contentValues.put(COL_2,product.productName);
        contentValues.put(COL_3,product.productBio);
        contentValues.put(COL_4,product.productPrice);

        long result = db.insert(TABLE_NAME, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<Product> getProductList()
    {
         SQLiteDatabase db = this.getReadableDatabase();

         // read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
         ArrayList<Product> productArrayList = new ArrayList<>();
         if (cursor.moveToFirst()) {
            do {
                //adding the data from cursor to our array list.
                Product product = new Product();
                product.productId = cursor.getString(0);
                product.productName= cursor.getString(1);
                product.productBio = cursor.getString(2);
                product.productPrice = cursor.getString(3);
              productArrayList.add(product);
            } while (cursor.moveToNext());
         }
         cursor.close();
        return productArrayList;
    }

    public void deleteProduct(Product item) {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("prod_table" ," ID=? " , new String[]{item.productId});
     }

     public void editData(Product product)
     {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues= new ContentValues();
         contentValues.put(COL_1,product.productId);
         contentValues.put(COL_2,product.productName);
         contentValues.put(COL_3,product.productBio);
         contentValues.put(COL_4,product.productPrice);

         db.update(TABLE_NAME, contentValues, "ID=?", new String[]{product.productId});
         db.close();
      }
}
