package com.albin.androidwebserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private WebServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch serverSwitch = (Switch) findViewById(R.id.server_status_button);
        serverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
                if (on)
                    try {
                        startServer();
                    } catch (IOException e) {
                        Log.e("Web Server", "Couldn't start server", e);
                    }
                else
                    stopServer();
            }
        });
    }

    private void startServer() throws IOException {
        server = new WebServer();
        server.setLogView((TextView) findViewById(R.id.server_log));
        server.start();
    }

    private void stopServer() {
        server.stop();
        server = null;
    }
}
