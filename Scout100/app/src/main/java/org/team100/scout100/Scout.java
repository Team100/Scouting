package org.team100.scout100;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Scout extends ActionBarActivity {

    private final String fileName = "ScoutStuff.txt";
    private String TAG = "ExternalFileWriteReadActivity";

    private EditText newTextBox;
    public void SaveData (View view) {
        EditText newTextBox = (EditText) findViewById(R.id.newTextBox);
        Toast toast = new Toast(getApplicationContext());
        toast.makeText(Scout.this, newTextBox.getText(),Toast.LENGTH_SHORT).show();
    }
   public File GetDocStorage(String docName) {
    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), docName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
    private static final String LOG_TAG = "SaveData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout);

        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {

            File outFile = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    fileName);

            if (!outFile.exists())
                copyImageToMemory(outFile);

            ImageView imageview = (ImageView) findViewById(R.id.image);
            imageview.setImageURI(Uri.parse("file://" + outFile.getAbsolutePath()));
        }
    }

    private void copyImageToMemory(File outFile) {
        try {

            BufferedOutputStream os = new BufferedOutputStream(
                    new FileOutputStream(outFile));

            BufferedInputStream is = new BufferedInputStream(getResources()
                    .openRawResource(R.raw.team100_launcher));

            copy(is, os);

        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException");
        }
    }

    private void copy(InputStream is, OutputStream os) {
        final byte[] buf = new byte[1024];
        int numBytes;
        try {
            while (-1 != (numBytes = is.read(buf))) {
                os.write(buf, 0, numBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException");

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scout, menu);
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
