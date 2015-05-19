package edu.washington.trosane.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ListView listview = (ListView) findViewById(R.id.listView);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, QuizApp.getInstance().getTopic());

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListView", "Position " + position + "Pressed");
                //set to next activity
                Intent nextActivity = new Intent(MainActivity.this, Overview.class);
                int a = position;
                nextActivity.putExtra("title", a);

                //send intent
                if(nextActivity.resolveActivity(getPackageManager()) != null) {
                    startActivity(nextActivity);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_list, menu);
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
            Intent nextActivity = new Intent(MainActivity.this, Preferences.class);

            //send intent
            if(nextActivity.resolveActivity(getPackageManager()) != null) {
                startActivity(nextActivity);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}