package com.ntukhpi.otp.nadirian.myninelab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Map<String, Object>> data = null;
    String[] items;
    private ListView lstNames;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @Nullable
    private ConnectivityChangeReceiver iConnectivityReceiver;
    @Nullable
    private Snackbar iConnectivitySnackbar;
    EditText userFilter;
    SimpleAdapter simpleAdapter;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userFilter=findViewById(R.id.userFilter);
        this.lstNames = (ListView) findViewById(R.id.lstNames);
        registerConnectivityReceiver();
        showContacts();
        simpleAdapter = new SimpleAdapter(this,
                data,
                android.R.layout.simple_list_item_2,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY, ContactsContract.CommonDataKinds.Phone.NUMBER},
                new int[]{android.R.id.text1, android.R.id.text2});
        lstNames.setAdapter(simpleAdapter);
        userFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (MainActivity.this).simpleAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            data = getContactNames();
            lstNames.setAdapter(new SimpleAdapter(this,
                    data,
                    android.R.layout.simple_list_item_2,
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY, ContactsContract.CommonDataKinds.Phone.NUMBER},
                    new int[]{android.R.id.text1, android.R.id.text2}
            ));

            lstNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Map<String, Object> item = (Map<String, Object>) parent.getAdapter().getItem(position);
                    final String phoneNumber = (String) item.get(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    dialPhoneNumber(phoneNumber);
                }
            });


        }


    }



    private List<Map<String, Object>> getContactNames() {
        final String[] wantedColumns = {
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        final String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY;
        final List<Map<String, Object>> data = new ArrayList<>();

        final ContentResolver resolver = getContentResolver();

        final Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, wantedColumns, null, null, sortOrder);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    final Map<String, Object> item = new HashMap<>();

                    item.put(ContactsContract.CommonDataKinds.Phone._ID, cursor.getString(
                            cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone._ID)));
                    item.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY, cursor.getString(
                            cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));
                    item.put(ContactsContract.CommonDataKinds.Phone.NUMBER, cursor.getString(
                            cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    data.add(item);
                }
            } finally {
                cursor.close();
            }
        }
        return data;
    }

    private void dialPhoneNumber(@NonNull String phoneNumber) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void handleConnectivityUpdated(boolean connectivityAvailable) {
        if (connectivityAvailable) {
            if (iConnectivitySnackbar != null && iConnectivitySnackbar.isShownOrQueued()) {
                iConnectivitySnackbar.dismiss();
            }
        } else {
            if (iConnectivitySnackbar == null) {
                iConnectivitySnackbar = createSnackbarForConnectivityAbsence();
            }
            if (!iConnectivitySnackbar.isShownOrQueued()) {
                iConnectivitySnackbar.show();
            }
        }
    }
    private void registerConnectivityReceiver() {
        if (iConnectivityReceiver == null) {
            iConnectivityReceiver = new ConnectivityChangeReceiver();

            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            this.registerReceiver(iConnectivityReceiver, filter);
        }
    }
    private void unregisterConnectivityReceiver() {
        if (iConnectivityReceiver != null) {
            this.unregisterReceiver(iConnectivityReceiver);
            iConnectivityReceiver = null;
        }
    }
    @Override
    protected void onDestroy() {
        unregisterConnectivityReceiver();
        super.onDestroy();
    }
    private void openConnectivitySetting() {
        final String[] settingsActions = {
                Settings.ACTION_WIFI_SETTINGS,
                Settings.ACTION_WIRELESS_SETTINGS,
                Settings.ACTION_SETTINGS
        };

        final PackageManager manager = getPackageManager();

        for (final String action : settingsActions) {
            final Intent intent = new Intent(action);
            if (manager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                // stop searching matching intents
                break;
            }
        }
    }
    private Snackbar createSnackbarForConnectivityAbsence() {
        return Snackbar
                .make(getWindow().getDecorView(), "No connectivity", Snackbar.LENGTH_INDEFINITE)
                .setAction("SETTINGS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openConnectivitySetting();
                    }
                });
    }
    private class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                final boolean noConnectivity =
                        intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                handleConnectivityUpdated(!noConnectivity);
            }
        }


    }

}
