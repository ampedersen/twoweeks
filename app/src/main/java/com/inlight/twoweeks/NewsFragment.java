package com.inlight.twoweeks;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anderspedersen on 09/09/16.
 */
public class NewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsObject>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    // URL for Guardian API
    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?q=love%20relationship&api-key=test";

    // Constant value for the Good News loader ID. The app will have just one loader, so this does
    // not really come into play.
    private static final int NEWS_LOADER_ID = 1;

    // Adapter for the list of Good News
    private NewsAdapter mAdapter;

    // EmptyStateTextView for the main list view
    private TextView mEmptyStateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Find reference to the {@link ListView} in the layout
        ListView goodNewsListView = (ListView) view.findViewById(R.id.list);

        // Find reference to the {@link EmptyStateTextView} that holds the EmptyState
        mEmptyStateTextView = (TextView) view.findViewById(R.id.empty_view);

        // Connect the EmptyStateTextView to the ListView
        goodNewsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of good news as input
        mAdapter = new NewsAdapter(getActivity(), new ArrayList<NewsObject>());

        // Set the adapter on the {@link ListView} so the list can be populated in the user interface
        goodNewsListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news.
        goodNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current news that was clicked on
                NewsObject currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri goodNewsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view th good news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, goodNewsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection go ahead and fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = view.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<NewsObject>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new NewsLoader(getActivity(), GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsObject>> loader, List<NewsObject> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<NewsObject>> loader) {

    }
}
