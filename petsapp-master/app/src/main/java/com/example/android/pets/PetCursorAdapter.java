package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;

/**
 * Created by nayir on 3/29/2017.
 */
public class PetCursorAdapter extends CursorAdapter {
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO: Fill out this method and return the list item view (instead of null)
        return LayoutInflater.from(context).inflate(R.layout.item_lay, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TODO: Fill out this method
        TextView Name = (TextView) view.findViewById(R.id.name);
        TextView Summary = (TextView) view.findViewById(R.id.summary);
        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME));
        String summ = cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED));
        // Populate fields with extracted properties
        Name.setText(name);
        Summary.setText(String.valueOf(summ));
    }
}

