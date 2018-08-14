package com.example.jesuschuajr.taplock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        Button tapbutton = findViewById(R.id.button);
        tapbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    int tapcount = 0;
    long startTime, difference, nextTime;
    ArrayList<Long> tapArr = new ArrayList<Long>();

    public void buttonOnClick(View v){

        TextView textView = findViewById(R.id.textView);
        Switch switch1 = findViewById(R.id.switch1);
        if (switch1.isChecked()){
            if (tapcount == 0){
                startTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
                //textView.setText("Start");
            }
            else{
                nextTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
                difference = nextTime - startTime;
                textView.setText(String.valueOf(difference));
                tapArr.add(difference);
                startTime = nextTime;
            }
            tapcount++;
        }
        else{
            textView.setText("Locked");
        }

    }

    public void buttonOnClick2(View v){
        Switch switch1 = findViewById(R.id.switch1);
        Button button = findViewById(R.id.button);

        if (switch1.isChecked()){
            TextView textView = findViewById(R.id.textView);
            tapcount = 0;
            textView.setText(String.valueOf(tapArr));
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            switch1.setChecked(false);

        }
        else{
            tapcount = 0;
        }
        tapArr = new ArrayList<Long>();

    }

    public void buttonOnClickToggle(View v){
        Button button = findViewById(R.id.button);
        Switch switch1 = findViewById(R.id.switch1);
        TextView textView = findViewById(R.id.textView);
        if (switch1.isChecked()){
            button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        } else button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        textView.setText("Locked");
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
