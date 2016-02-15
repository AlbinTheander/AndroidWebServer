package com.albin.androidwebserver;

import android.util.Log;
import android.widget.TextView;

import com.albin.androidwebserver.data.ResponseData;
import com.google.gson.Gson;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class WebServer extends NanoHTTPD {

    private TextView logView;

    public WebServer() {
        super(8080);
    }

    public void setLogView(TextView logView) {
        this.logView = logView;
    }

    private void log(final String s) {
        Log.d("Web Server", s);
        if (logView != null) {
            final TextView lv = logView;
            lv.post(new Runnable() {
                @Override
                public void run() {
                    lv.append(s + "\n");
                }
            });
        }
    }

    @Override
    public void start() throws IOException {
        log("Starting server...");
        super.start();
    }

    @Override
    public void stop() {
        log("Stopping server...");
        super.stop();
        logView = null;
    }

    @Override
    public Response serve(IHTTPSession session) {
        log("Serving request");
        ResponseData data = new ResponseData(42, "Good Luck!");
        String json = new Gson().toJson(data);
        return newFixedLengthResponse(Response.Status.OK, "application/json", json);
    }
}
