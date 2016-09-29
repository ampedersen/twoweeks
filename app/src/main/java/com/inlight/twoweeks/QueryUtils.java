package com.inlight.twoweeks;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anderspedersen on 28/09/16.
 */

public class QueryUtils {

    // Tag for Log messages
    private static final String LOG_TAG = QueryUtils.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    // Query the Guardian dataset and return a list of GoodNews objects.
    public static List<NewsObject> fetchGoodNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);
        Log.i("test", "requestURL before doing the request: " + requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<NewsObject> news = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return news;
    }

    private static List<NewsObject> extractFeatureFromJson(String jsonResponse) {
        Log.i("test", "jsonResponse after call to ExtractFeature: " + jsonResponse.toString());
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Good News to.
        List<NewsObject> news = new ArrayList<>();

        // Try to parse the JSON response string. If theres a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the ecveption so the app does not crash, and print the error message to the log.

        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            JSONObject responseObject = baseJsonResponse.getJSONObject("response");

            // Extract the JSONArray associated with the key called "results"
            JSONArray goodnewsArray = responseObject.getJSONArray("results");

            Log.i("test", "goodnewsArray after pairing with results" + goodnewsArray.toString());
            Log.i("test", "lol");

            // For each good news in the goodnewsArray, create a GoodNews Object
            for (int i = 0; i < goodnewsArray.length();i++) {

                // Get a single good news at the position i within the list
                JSONObject currentGoodNews = goodnewsArray.getJSONObject(i);

                // Extract the value for the key called webTitle
                String header = currentGoodNews.getString("webTitle");

                // Extract the value for the key called SectionName
                String section = currentGoodNews.getString("sectionName");

                // Extract the value for the key called webPublicationDate
                String publish = currentGoodNews.getString("webPublicationDate");

                // Extract the value for the key called webUrl
                String url = currentGoodNews.getString("webUrl");

                NewsObject singleGoodNews = new NewsObject(header, section, publish, url);

                Log.i("test", "singleGoodNews object before adding it to the Array");
                news.add(singleGoodNews);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        Log.i("test", "goodnews: " + news.toString());
        return news;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Good News JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }

        return url;
    }
}
