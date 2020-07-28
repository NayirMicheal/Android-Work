package com.example.nayir.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Nowplay, search, Store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nowplay = (TextView) findViewById(R.id.nwpl);
        search = (TextView) findViewById(R.id.sea);
        Store = (TextView) findViewById(R.id.stron);

        Nowplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nwplay = new Intent(MainActivity.this, NowPlaying.class);
                startActivity(nwplay);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(MainActivity.this, Search.class);
                startActivity(search);
            }
        });

        Store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stor = new Intent(MainActivity.this, StoreScreen.class);
                startActivity(stor);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
