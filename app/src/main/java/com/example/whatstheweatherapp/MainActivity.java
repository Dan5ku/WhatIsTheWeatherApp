package com.example.whatstheweatherapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_stockholmWeather, btn_brazilWeather;
    ListView lv_weatherReports;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign values to controllers on layout
        btn_stockholmWeather = findViewById(R.id.btn_getStockholmWeather);
        btn_brazilWeather = findViewById(R.id.btn_getBrazilWeather);

        // click listener for button
        btn_stockholmWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.open-meteo.com/v1/forecast?latitude=59.3328&longitude=18.0645&current_weather=true&timezone=auto";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String currentTemp = "";
                        String currentWeather = "";
                        int currentWeather2 = 0;

                        try{
                            JSONObject cityInfo = response.getJSONObject("current_weather");
                            currentTemp = cityInfo.getString("temperature");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try{
                            JSONObject cityInfo = response.getJSONObject("current_weather");
                            currentWeather2 = cityInfo.getInt("weathercode");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (currentWeather2 == 0) {
                            currentWeather = "Clear sky";
                        } else if (currentWeather2 >= 1 && currentWeather2 <= 3) {
                            currentWeather = "Mainly clear, partly cloudy, and overcast";
                        } else if (currentWeather2 >= 45 && currentWeather2 <= 48) {
                            currentWeather = "Fog and depositing rime fog";
                        } else if (currentWeather2 >= 51 && currentWeather2 <= 55) {
                            currentWeather = "Drizzle: Light, moderate, and dense intensity";
                        } else if (currentWeather2 >= 56 && currentWeather2 <= 57) {
                            currentWeather = "Freezing Drizzle: Light and dense intensity";
                        } else if (currentWeather2 >= 61 && currentWeather2 <= 65) {
                            currentWeather = "Rain: Slight, moderate and heavy intensity";
                        } else if (currentWeather2 >= 66 && currentWeather2 <= 67) {
                            currentWeather = "Freezing Rain: Light and heavy intensity";
                        } else if (currentWeather2 >= 71 && currentWeather2 <= 76) {
                            currentWeather = "Snow fall: Slight, moderate, and heavy intensity";
                        } else if (currentWeather2 == 77) {
                            currentWeather = "Snow grains";
                        } else if (currentWeather2 >= 80 && currentWeather2 <= 82) {
                            currentWeather = "Rain showers: Slight, moderate, and violent";
                        } else if (currentWeather2 >= 85 && currentWeather2 <= 86) {
                            currentWeather = "Snow showers slight and heavy";
                        } else if (currentWeather2 == 95) {
                            currentWeather = "Thunderstorm: Slight or moderate";
                        } else {
                            currentWeather = "Thunderstorm with slight and heavy hail";
                        }

                        /* old code
                        // Toast.makeText(MainActivity.this, "The Current Temperature In Stockholm Is: " + currentTemp + " Celsius And There Is " + currentWeather, Toast.LENGTH_LONG).show();
                        */

                        /// Create a custom toast

                        //Creating the layoutInflater instance
                        LayoutInflater li = getLayoutInflater();

                        //Getting the view object as defined in the toast_xml file
                        View layout = li.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.toast_layout_root));

                        //set textView at runtime to the custom toast textView
                        //create a object for the textView in custom toast
                        TextView text = (TextView)layout.findViewById(R.id.text);
                        text.setText("The Temperature In Stockholm Is: " + currentTemp + "°C \nAnd There Is " + currentWeather);

                        //Creating the Toast object
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setView(layout);
                        toast.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);
            }

        });
        // click listener for button
        btn_brazilWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.open-meteo.com/v1/forecast?latitude=-15.7801&longitude=-47.9292&current_weather=true&timezone=auto";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String currentTemp = "";
                        String currentWeather = "";
                        int currentWeather2 = 0;

                        try{
                            JSONObject cityInfo = response.getJSONObject("current_weather");
                            currentTemp = cityInfo.getString("temperature");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try{
                            JSONObject cityInfo = response.getJSONObject("current_weather");
                            currentWeather = cityInfo.getString("weathercode");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (currentWeather2 == 0) {
                            currentWeather = "Clear sky";
                        } else if (currentWeather2 >= 1 && currentWeather2 <= 3) {
                            currentWeather = "Mainly clear, partly cloudy, and overcast";
                        } else if (currentWeather2 >= 45 && currentWeather2 <= 48) {
                            currentWeather = "Fog and depositing rime fog";
                        } else if (currentWeather2 >= 51 && currentWeather2 <= 55) {
                            currentWeather = "Drizzle: Light, moderate, and dense intensity";
                        } else if (currentWeather2 >= 56 && currentWeather2 <= 57) {
                            currentWeather = "Freezing Drizzle: Light and dense intensity";
                        } else if (currentWeather2 >= 61 && currentWeather2 <= 65) {
                            currentWeather = "Rain: Slight, moderate and heavy intensity";
                        } else if (currentWeather2 >= 66 && currentWeather2 <= 67) {
                            currentWeather = "Freezing Rain: Light and heavy intensity";
                        } else if (currentWeather2 >= 71 && currentWeather2 <= 76) {
                            currentWeather = "Snow fall: Slight, moderate, and heavy intensity";
                        } else if (currentWeather2 == 77) {
                            currentWeather = "Snow grains";
                        } else if (currentWeather2 >= 80 && currentWeather2 <= 82) {
                            currentWeather = "Rain showers: Slight, moderate, and violent";
                        } else if (currentWeather2 >= 85 && currentWeather2 <= 86) {
                            currentWeather = "Snow showers slight and heavy";
                        } else if (currentWeather2 == 95) {
                            currentWeather = "Thunderstorm: Slight or moderate";
                        } else {
                            currentWeather = "Thunderstorm with slight and heavy hail";
                        }
                        /*old code
                        //Toast.makeText(MainActivity.this, "The Current Temperature In Brazil Is: " + currentTemp + " Celsius And There Is " + currentWeather, Toast.LENGTH_LONG).show();
                        */

                        /// Create a custom toast

                        //Creating the layoutInflater instance
                        LayoutInflater li = getLayoutInflater();

                        //Getting the view object as defined in the toast_xml file
                        View layout = li.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.toast_layout_root));

                        //set textView at runtime to the custom toast textView
                        //create a object for the textView in custom toast
                        TextView text = (TextView)layout.findViewById(R.id.text);
                        text.setText("The Temperature In Brazil Is: " + currentTemp + "°C \nAnd There Is " + currentWeather);

                        //Creating the Toast object
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setView(layout);
                        toast.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);
            }
        });
    }
}
