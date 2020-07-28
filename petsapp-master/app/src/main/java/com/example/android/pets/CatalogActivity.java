/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int URI_LOADER = 0;
    SQLiteDatabase db;
    ListView lvItems;
    PetCursorAdapter adapter;
    private PetDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        lvItems = (ListView) findViewById(R.id.lstview);
        View emptyView = findViewById(R.id.empty_view);
        lvItems.setEmptyView(emptyView);

        adapter = new PetCursorAdapter(this, null);
        lvItems.setAdapter(adapter);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new PetDbHelper(this);
        db = mDbHelper.getWritableDatabase();
        getLoaderManager().initLoader(URI_LOADER, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertdummy();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertdummy() {
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME, "toto");
        values.put(PetContract.PetEntry.COLUMN_PET_BREED, "terrier");
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER, PetContract.PetEntry.GENDER_MALE);
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, 7);
        Uri URI_ID = getContentResolver().insert(PetContract.PetEntry.CONTENT_URI, values);
        if (URI_ID == null)
            Toast.makeText(getApplicationContext(), R.string.fail_save, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), R.string.success_save, Toast.LENGTH_LONG).show();

    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private void displayDatabaseInfo() {
//        // Perform this raw SQL query "SELECT * FROM pets"
//        // to get a Cursor that contains all rows from the pets table.
//        String[] projection = {PetContract.PetEntry._ID,
//                PetContract.PetEntry.COLUMN_PET_NAME,
//                PetContract.PetEntry.COLUMN_PET_BREED};
//        String selection = null;
//        String selectionArgs[] =null;
//        Cursor cursor=getContentResolver().query(PetContract.PetEntry.CONTENT_URI,projection,selection,selectionArgs,null,null);
//        /*Cursor cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection,
//                selection, selectionArgs,
//                null, null, null);*/
//
//            // Setup cursor adapter using cursor from last step
//            // Attach cursor adapter to the ListView
//    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED};
        String selection = null;
        String selectionArgs[] = null;
        return new CursorLoader(getApplicationContext(), ContactsContract.Data.CONTENT_URI,
                projection, selection, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
