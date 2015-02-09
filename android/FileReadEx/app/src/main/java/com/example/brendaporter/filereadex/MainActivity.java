package com.example.brendaporter.filereadex;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    public static final String IS_READ = "org.team100.scouting.IS_READ";
    public static final int READ_WRITE_ACTIVITY_REQUEST_CODE = 0;
    public static final String MATCHES = "org.team100.scouting.MATCHES";

    private String mMatches = "";

    public void readFile(View view) {
        Intent readWriteIntent = new Intent(this, ReadWriteActivity.class);
        readWriteIntent.putExtra(IS_READ, true);
        startActivityForResult(readWriteIntent, READ_WRITE_ACTIVITY_REQUEST_CODE);
    }

    public void writeFile(View view) {
        Intent readWriteIntent = new Intent(this, ReadWriteActivity.class);
        readWriteIntent.putExtra(IS_READ, false);
        readWriteIntent.putExtra(MATCHES, mMatches);
        startActivity(readWriteIntent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Activity Result Not Ok!", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (requestCode) {
            case READ_WRITE_ACTIVITY_REQUEST_CODE:
                mMatches = data.getStringExtra(MATCHES);
                break;
            default:
                break;
        }
    }

    private static final String TAG = MainActivity.class.getName();
    private static final String FILENAME = "myFile.txt";
    private static final String LOG_TAG = "FileReadEx";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String textToSaveString = "Team 100";

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
            Toast.makeText(getApplicationContext(), "oh sh-", Toast.LENGTH_SHORT).show();
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