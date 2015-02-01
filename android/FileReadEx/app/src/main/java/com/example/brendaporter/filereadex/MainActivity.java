package com.example.brendaporter.filereadex;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String FILENAME = "myFile.txt";
    private static final String LOG_TAG = "FileReadEx";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String textToSaveString = "Hello Earth";

        ReadWriteFile.writeToFile(this, FILENAME, textToSaveString);

        String textFromFileString =  ReadWriteFile.readFromFile(this, FILENAME);


        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getApplicationContext(), "MEDIA MOUNTED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "NO MEDIA MOUNTED", Toast.LENGTH_SHORT).show();
        }

        if ( textToSaveString.equals(textFromFileString) ) {
            Toast.makeText(getApplicationContext(), "both strings are equal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "there is a problem", Toast.LENGTH_SHORT).show();
        }
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