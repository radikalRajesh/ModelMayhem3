package modelmayhem.com.modelmayhem.loginresistration;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SimpleAdapter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import modelmayhem.com.modelmayhem.R;
import modelmayhem.com.modelmayhem.task.PlaceJSONParser;

/**
 * Created by Arpan on 12/6/2017.
 */

public class LocationActivity extends Activity implements View.OnClickListener{

    AutoCompleteTextView tvPlaces;
    PlacesTask placesTask;
    ParserTask parserTask;
    Button Continue_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        InitButton();



        tvPlaces.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                placesTask = new PlacesTask();
                placesTask.execute(s.toString());

            }
        });


    }

    private void InitButton() {
        tvPlaces = (AutoCompleteTextView) findViewById(R.id.tv_places);
        tvPlaces.setThreshold(1);
        Continue_btn = (Button)findViewById(R.id.btn_continue);
        Continue_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_relativeLayout:

                BackFunction();
                break;

            case R.id.btn_continue:
                ContinueBtn();
                break;

        }


    }
    private void ContinueBtn() {
        Intent intent = new Intent(this,ChooseUsername.class);
        startActivity(intent);

    }
    private void BackFunction() {
        finish();
    }


    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException, IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
            // Connecting to url
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }
            data = sb.toString();
            br.close();

        }catch(Exception e){

        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }



    // Fetches all places from GooglePlaces AutoComplete Web Service
    private class PlacesTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... place) {
            // For storing data from web service
            String data = "";
            // Obtain browser key from https://code.google.com/apis/console

            String key = "key=AIzaSyCYdGQs3alHsOMkPkRmCXjdWdJT1MORgeg";
            String input="";
            try {
                input = "input=" + URLEncoder.encode(place[0], "utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            // place type to be searched
            String types = "types=geocode";
            // Sensor enabled
            String sensor = "sensor=false";
            // Building the parameters to the web service
            String parameters = input+"&"+types+"&"+sensor+"&"+key;
            // Output format
            String output = "json";
            // Building the url to the web service
            String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;
            try{
                // Fetching the data from we service
                data = downloadUrl(url);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Creating ParserTask
            parserTask = new ParserTask();

            // Starting Parsing the JSON string returned by Web Service
            parserTask.execute(result);
        }
    }
    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>> {

        JSONObject jObject;
        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {
            List<HashMap<String, String>> places = null;
            PlaceJSONParser placeJsonParser = new PlaceJSONParser();
            try{
                jObject = new JSONObject(jsonData[0]);
                // Getting the parsed data as a List construct
                places = placeJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {

            String[] from = new String[] { "description"};
            int[] to = new int[] { android.R.id.text1 };

            // Creating a SimpleAdapter for the AutoCompleteTextView
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);

            // Setting the adapter
            tvPlaces.setAdapter(adapter);
        }
    }


}
