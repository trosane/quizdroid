package edu.washington.trosane.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("url");

        // For our recurring task, we'll just display a message
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
    }
}