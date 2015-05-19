package edu.washington.trosane.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Preferences extends ActionBarActivity {
    private PendingIntent pendingIntent;
    EditText link;
    EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_activity);

        /* Retrieve a PendingIntent that will perform a broadcast */
        final Intent alarmIntent = new Intent(Preferences.this, MyReceiver.class);


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link = (EditText) findViewById(R.id.url);
                time = (EditText) findViewById(R.id.time);
                String url = link.getText().toString();
                String num = time.getText().toString();
                if (!num.equals("") && !url.equals("")) {
                    alarmIntent.putExtra("url", url);
                    pendingIntent = PendingIntent.getBroadcast(Preferences.this, 0, alarmIntent, 0);
                    start();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preferences, menu);
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

    @Override
    protected void onDestroy() {

        super.onDestroy();
        cancel();
    }

    public void start() {
        time = (EditText) findViewById(R.id.time);
        String timer = time.getText().toString();
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        int minute = Integer.parseInt(timer) * 60000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), minute, pendingIntent);
        Toast.makeText(this, "Starting download as soon as possible...", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }
}