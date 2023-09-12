package com.example.productlist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ProductListItemAdapter extends RecyclerView.Adapter<ProductListItemAdapter.ViewHolder>
{
    DatabaseHelper myDb;

    private ArrayList<Product> data;
    private Context mContext;
    public ProductListItemAdapter(ArrayList<Product> data, DatabaseHelper myDb,Context context)
    {
        mContext = context;
        this.data = data;
        this.myDb = myDb;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product item = data.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewId;
        private TextView mTextViewName;
        private TextView mTextViewBio;
        private TextView mTextViewPrice;
        private Button mDeleteButton;
        private Button mEditButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewId = itemView.findViewById(R.id.textViewId);
            mTextViewName = itemView.findViewById(R.id.textViewName);
            mTextViewBio = itemView.findViewById(R.id.textViewBio);
            mTextViewPrice = itemView.findViewById(R.id.textViewPrice);
            mDeleteButton = itemView.findViewById(R.id.delete);
            mEditButton = itemView.findViewById(R.id.editProButton);
            itemView.setOnClickListener(this);

        }

        public void bind(Product item) {
            mTextViewName.setText("Product Name:  "+ item.productName);

            mTextViewId.setVisibility(View.GONE);
            mTextViewBio.setVisibility(View.GONE);
            mTextViewPrice.setVisibility(View.GONE);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDb.deleteProduct(item);
                    data.remove(item);
                    notifyItemRemoved(getAdapterPosition());

                    Toast.makeText(mContext, "Product item is deleted successfully", Toast.LENGTH_SHORT).show();
                }
            });
            editButtonClick(item);

        }

         public void editButtonClick(Product product){
            mEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      Intent intent = new Intent(mContext, EditProductActivity.class);
                    intent.putExtra("Id",product.productId);
                    intent.putExtra("Name", product.productName);
                    intent.putExtra("Bio", product.productBio );
                    intent.putExtra("Price", product.productPrice);

                     mContext.startActivity(intent);
                }
            });
         }


        @Override
        public void onClick(View v) {
            //Handle item click here
            int position = getAdapterPosition();
            Product product = data.get(position);
            Intent intent = new Intent(mContext,ProductDetailsActivity.class);
            intent.putExtra("Id",product.productId);
            intent.putExtra("Name", product.productName);
            intent.putExtra("Bio", product.productBio );
            intent.putExtra("Price", product.productPrice);

            mContext.startActivity(intent);

        }
    }
}

