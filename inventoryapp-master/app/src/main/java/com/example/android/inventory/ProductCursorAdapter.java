package com.example.android.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.data.InventoryContract;

/**
 * Created by nayir on 3/31/2017.
 */
public class ProductCursorAdapter extends CursorAdapter {
    private Context con;
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        con = context;
        return LayoutInflater.from(context).inflate(R.layout.lst_item, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final String name, quan, pri;
        final TextView price;
        final int id;
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView quantity = (TextView) view.findViewById(R.id.quantity);
        price = (TextView) view.findViewById(R.id.price);
        ImageButton imbtn = (ImageButton) view.findViewById(R.id.sale);
        // Extract properties from cursor

        id = cursor.getInt(cursor.getColumnIndex(InventoryContract.ProductEntry._ID));
        name = cursor.getString(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME));
        quan = cursor.getString(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY));
        pri = cursor.getString(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE));
        // Populate fields with extracted properties
        Name.setText(name);
        quantity.setText(quan);
        price.setText(pri + " $");

        imbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reduce quantity by one and update the database with the new quantity
                ContentValues values = new ContentValues();
                values.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, Integer.parseInt(quan) - 1);
                //todo check last line running or not
                int rowAffected = con.getContentResolver().update(ContentUris.withAppendedId(InventoryContract.ProductEntry.CONTENT_URI, id), values, null, null);
                if (rowAffected != 0) {
                    // If no rows were affected, then there was an error with the update.
                    Toast.makeText(con, "ok :" + rowAffected, Toast.LENGTH_SHORT).show();
                } else {
                    // Otherwise, the update was successful and we can display a toast.
                    Toast.makeText(con, "failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
