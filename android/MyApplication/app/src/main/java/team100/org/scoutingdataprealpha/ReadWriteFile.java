package team100.org.scoutingdataprealpha;

/**
 * Created by brendaporter on 2/14/15.
 */
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by brendaporter on 2/1/15.
 */

public class ReadWriteFile {
    private static final String TAG = ReadWriteFile.class.getName();

    public static void writeToFile(Context context, String filename, String data) {
        try {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File dir = new File(root.getAbsolutePath());
            dir.mkdirs();

            File f = new File(dir, filename);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(f));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Boolean exists = f.exists();
            Boolean hidden = f.isHidden();
            String[] files = dir.list();

            Log.i(TAG, "file properties: e = " + exists + " h = " + hidden);

            MediaScannerConnection.scanFile(context,
                    new String[]{f.toString()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned " + path + ":");
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }

    }

    public static String readFromFile(Context context, String filename) {

        String ret = "";

        try {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File dir = new File(root.getAbsolutePath());
            dir.mkdirs();

            File f = new File(dir, filename);
            InputStream inputStream = new FileInputStream(f);
            Log.d("emily", inputStream.toString());

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                ArrayList Match = new ArrayList();
                ArrayList Team = new ArrayList();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString).append("\n");
                    Match.add(0, stringBuilder);
                    for (int i = 0; i < 6; i++) {
                        Team.add(i, stringBuilder);
                    }
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }

        return ret;
    }
}
