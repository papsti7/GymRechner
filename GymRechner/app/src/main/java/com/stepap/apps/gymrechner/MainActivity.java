package com.stepap.apps.gymrechner;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Remove title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_entry);
        //set screenbrightness to 100% to have better color feel
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void calculateWeights(View view) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        EditText editText = (EditText) findViewById(R.id.et_weight);
        int weight;
        if(editText.getText().toString().length() != 0)
        {
            weight = Integer.parseInt(editText.getText().toString());
        }
        else
        {
            TextView textView = (TextView)findViewById(R.id.tv_todo);
            textView.setText("Geben Sie bitte einen Wert ein!");
            return;
        }

        Map<Integer, Integer> results = new TreeMap<>();

        for (float i = 0.8f; i > 0.2f; i -= 0.2f) {
            results.put((int) (i * 100), (int) (weight * i));
        }

        results.put(50, (int) (weight * 0.5f));


        System.out.println(results);


        setContentView(R.layout.activity_results);
        TextView tv_results = (TextView)findViewById(R.id.tv_result80);
        tv_results.setText("80% of " + weight + " : \t\t\t\t" + results.get(80));

        tv_results = (TextView)findViewById(R.id.tv_result60);
        tv_results.setText("60% of " + weight + " : \t\t\t\t" + results.get(60));

        tv_results = (TextView)findViewById(R.id.tv_result50);
        tv_results.setText("50% of " + weight + " : \t\t\t\t" + results.get(50));

        tv_results = (TextView)findViewById(R.id.tv_result40);
        tv_results.setText("40% of " + weight + " : \t\t\t\t" + results.get(40));

    }

    public void back(View view){
        setContentView(R.layout.activity_entry);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.stepap.apps.gymrechner/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.stepap.apps.gymrechner/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
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
    }*/
}
