package com.inlight.twoweeks;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by anderspedersen on 28/09/16.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsObject>> {

    // Tag for log messages
    private static final String LOG_TAG = NewsLoader.class.getName();

    // Query URL
    private String mUrl;

    // Constructor
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // This is on a background thread.
    @Override
    public List<NewsObject> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of Good News.
        List<NewsObject> news = QueryUtils.fetchGoodNewsData(mUrl);
        return news;
    }
}