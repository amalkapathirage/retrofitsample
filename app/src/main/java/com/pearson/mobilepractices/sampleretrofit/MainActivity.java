package com.pearson.mobilepractices.sampleretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestClient.get().getWeather("California", new Callback<WeatherResponse>() {
            @Override
            public void success(WeatherResponse weatherResponse, Response response) {
                // success!
                Log.i("App", weatherResponse.main.humidity);
                Log.i("App", weatherResponse.name);
                Log.i("App", weatherResponse.main.temp);

                render(weatherResponse);

            }

            @Override
            public void failure(RetrofitError error) {
                // something went wrong
                Log.i("App", "something went wrong"+ error.getUrl());
               error.printStackTrace();
            }
        });
    }

    private void render(WeatherResponse weatherResponse) {
        TextView view1 = (TextView) findViewById(R.id.text1);
        TextView view2 = (TextView) findViewById(R.id.text2);
        TextView view3 = (TextView) findViewById(R.id.text3);

        view1.setText(weatherResponse.name);
        view2.setText("temp " + weatherResponse.main.temp);
        view3.setText("humidity " + weatherResponse.main.humidity);
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
}
