package com.saferoam.saferoam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.getpebble.android.kit.PebbleKit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    @Override
    protected void onResume() {
        super.onResume();

        // Construct output String
        StringBuilder builder = new StringBuilder();
        builder.append("Pebble Info\n\n");

        // Is the watch connected?
        boolean isConnected = PebbleKit.isWatchConnected(this);
        builder.append("Watch connected: " + (isConnected ? "true" : "false")).append("\n");

        // What is the firmware version?
        PebbleKit.FirmwareVersionInfo info = PebbleKit.getWatchFWVersion(this);
        builder.append("Firmware version: ");
        builder.append(info.getMajor()).append(".");
        builder.append(info.getMinor()).append("\n");

        // Is AppMesage supported?
        boolean appMessageSupported = PebbleKit.areAppMessagesSupported(this);
        builder.append("AppMessage supported: " + (appMessageSupported ? "true" : "false"));

        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText(builder.toString());

    }
}
