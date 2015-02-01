package org.team100.scout100;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.team100.scout100.p2p2.WiFiDirectBroadcastReceiver;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver mReceiver;

    IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public final static String EXTRA_MESSAGE = "org.team100.scout100.MESSAGE";
    public final static String COLOR_MESSAGE = "org.team100.scout100.TXT_COLOR";
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        int txtColor = 0;
        RadioGroup rgColors = (RadioGroup) findViewById(R.id.radioGroup);
        if(rgColors.getCheckedRadioButtonId() != -1) {
            int id= rgColors.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) rgColors.findViewById(id);
            txtColor = radioButton.getCurrentTextColor();
        }
        intent.putExtra(COLOR_MESSAGE, txtColor);
        startActivity(intent);
    }

    public void ButtonToScout (View view) {
        
    }

  //  public void adAdRoRo (View view){
   //     createToast("carPOOL?");
   // }

    public void discoverDevices(View view) {
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                createToast("Discover Peers Success!!");
            }

            @Override
            public void onFailure(int reasonCode) {
                createToast("Discover Peers Failed.");
            }
        });
    }

    public void createToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }


    HashMap<String, WifiP2pDevice> mDeviceMap = new HashMap<String, WifiP2pDevice>();
    WifiP2pManager.PeerListListener mPeerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peers) {
            createToast("onPeersAvailable: " + peers.getDeviceList().size());
            LinearLayout layout = (LinearLayout) findViewById(R.id.devicesLayout);
            layout.removeAllViews();
            mDeviceMap.clear();
            for(WifiP2pDevice device : peers.getDeviceList()) {
                mDeviceMap.put(device.deviceAddress, device);
                Button b = new Button(getApplicationContext());
                b.setText(device.deviceName + " (" + device.deviceAddress + ")");
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Button b = (Button) v;
                        String btnTxt = (String)b.getText();
                        String address = btnTxt.substring(btnTxt.lastIndexOf('(') + 1, btnTxt.lastIndexOf(')'));

                        //obtain a peer from the WifiP2pDeviceList
                        final WifiP2pDevice device = mDeviceMap.get(address);
                        WifiP2pConfig config = new WifiP2pConfig();
                        config.deviceAddress = device.deviceAddress;
                        mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

                            @Override
                            public void onSuccess() {
                                createToast("Connected To " + device.deviceAddress);
                            }

                            @Override
                            public void onFailure(int reason) {
                                createToast("Connection Failed For " + device.deviceAddress);
                            }
                        });
                    }
                });
                layout.addView(b);
            }
        }
    };

    public WifiP2pManager.PeerListListener getPeerListListener() { return mPeerListListener; }

/* TODO(daden): Finish the DataServer. */
//    public static class FileServerAsyncTask extends AsyncTask {
//
//        private Context context;
//        private TextView statusText;
//
//        public FileServerAsyncTask(Context context, View statusText) {
//            this.context = context;
//            this.statusText = (TextView) statusText;
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            try {
//
//                /**
//                 * Create a server socket and wait for client connections. This
//                 * call blocks until a connection is accepted from a client
//                 */
//                ServerSocket serverSocket = new ServerSocket(8888);
//                Socket client = serverSocket.accept();
//
//                /**
//                 * If this code is reached, a client has connected and transferred data
//                 * Save the input stream from the client as a JPEG file
//                 */
//                final File f = new File(Environment.getExternalStorageDirectory() + "/"
//                        + context.getPackageName() + "/wifip2pshared-" + System.currentTimeMillis()
//                        + ".jpg");
//
//                File dirs = new File(f.getParent());
//                if (!dirs.exists())
//                    dirs.mkdirs();
//                f.createNewFile();
//                InputStream inputstream = client.getInputStream();
//                copyFile(inputstream, new FileOutputStream(f));
//                serverSocket.close();
//                return f.getAbsolutePath();
//            } catch (IOException e) {
//                Log.e(WiFiDirectActivity.TAG, e.getMessage());
//                return null;
//            }
//        }
//
//        /**
//         * Start activity that can handle the JPEG image
//         */
//        @Override
//        protected void onPostExecute(String result) {
//            if (result != null) {
//                statusText.setText("File copied - " + result);
//                Intent intent = new Intent();
//                intent.setAction(android.content.Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.parse("file://" + result), "image/*");
//                context.startActivity(intent);
//            }
//        }
//    }
//
//    public void sendP2pData() {
//        Context context = this.getApplicationContext();
//        String host;
//        int port;
//        int len;
//        Socket socket = new Socket();
//        byte buf[]  = new byte[1024];
//
//        try {
//            /**
//             * Create a client socket with the host,
//             * port, and timeout information.
//             */
//            socket.bind(null);
//            socket.connect((new InetSocketAddress(host, port)), 500);
//
//            /**
//             * Create a byte stream from a JPEG file and pipe it to the output stream
//             * of the socket. This data will be retrieved by the server device.
//             */
//            OutputStream outputStream = socket.getOutputStream();
//            ContentResolver cr = context.getContentResolver();
//            InputStream inputStream = null;
//            inputStream = cr.openInputStream(Uri.parse("path/to/picture.jpg"));
//            while ((len = inputStream.read(buf)) != -1) {
//                outputStream.write(buf, 0, len);
//            }
//            outputStream.close();
//            inputStream.close();
//        } catch (FileNotFoundException e) {
//            //catch logic
//        } catch (IOException e) {
//            //catch logic
//        }
//
//        /**
//         * Clean up any open sockets when done
//         * transferring or if an exception occurred.
//         */
//        finally {
//            if (socket != null) {
//                if (socket.isConnected()) {
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        //catch logic
//                    }
//                }
//            }
//        }
//    }
}