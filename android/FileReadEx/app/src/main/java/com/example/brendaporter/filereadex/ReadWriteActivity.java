package com.example.brendaporter.filereadex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;


public class ReadWriteActivity extends ActionBarActivity {

    public static final String RWTEXTVIEW_KEY = "org.team100.scouting.rwtextview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);

        Intent intent = getIntent();
        Boolean isRead = intent.getBooleanExtra(MainActivity.IS_READ, true);
        String matches = intent.getStringExtra(MainActivity.MATCHES);

        TextView rwtextview = (TextView) findViewById(R.id.rwtextview);
        if (matches != null && !matches.equals("")) {
            rwtextview.setText(matches);
        }

        if (isRead) {
            Toast.makeText(getApplicationContext(), "Read Activity!", Toast.LENGTH_SHORT).show();
            String contents = ReadWriteFile.readFromFile(this, "matches.csv");
            Scanner linescanner = new Scanner(contents);

            String line;
            while(true){
                line = linescanner.nextLine();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");
                String prev = scanner.next();
                String prev2 = scanner.next();
                String prev3 = scanner.next();
                String prev4 = scanner.next();
                String prev5 = scanner.next();
                String prev6 = scanner.next();
                String prev7 = scanner.next();
                String result = prev + " " + prev2 + " " + prev3 + " " + prev4 + " " + prev5 + " " + prev6 + " " + prev7;
                rwtextview.setText(result);
                Log.d("emily", result);
                intent.putExtra(MainActivity.MATCHES, line);
                setResult(RESULT_OK, intent);
                scanner.close();
            }
        }

        else {
            Toast.makeText(getApplicationContext(), "Write Activity!", Toast.LENGTH_SHORT).show();
            String contents = rwtextview.getText().toString();
            ReadWriteFile.writeToFile(this, "scouting_out.csv", contents);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Saved the Instance!", Toast.LENGTH_SHORT).show();
        super.onSaveInstanceState(savedInstanceState);
        TextView rwtextview = (TextView) findViewById(R.id.rwtextview);
        String contents = rwtextview.getText().toString();
        savedInstanceState.putString(RWTEXTVIEW_KEY, contents);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Restored the Instance!", Toast.LENGTH_SHORT).show();
        super.onRestoreInstanceState(savedInstanceState);
        String contents = savedInstanceState.getString(RWTEXTVIEW_KEY);
        TextView rwtextview = (TextView) findViewById(R.id.rwtextview);
        rwtextview.setText(contents);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_write, menu);
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
