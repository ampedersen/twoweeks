package com.inlight.twoweeks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anderspedersen on 28/09/16.
 */

public class NewsAdapter extends ArrayAdapter<NewsObject> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news is the list of earthquakes, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<NewsObject> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {

            // Inflate the layout
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);

            // Setup the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.headerTv = (TextView) listItemView.findViewById(R.id.header);
            viewHolder.sectionTv = (TextView) listItemView.findViewById(R.id.section);
            viewHolder.publishedTv = (TextView) listItemView.findViewById(R.id.publish_date);

            listItemView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // Find the good news at the given position in the list of good news
        NewsObject currentNews = getItem(position);

        if (currentNews != null) {
            viewHolder.headerTv.setText(currentNews.getHeader());
            viewHolder.sectionTv.setText(currentNews.getSection());
            viewHolder.publishedTv.setText(getDate(currentNews.getPublish()));
        }

        return listItemView;
    }

    private String getDate(String publish) {
        // Simple method to extract the date from the combined date and time return from the API
        String date;
        date = publish;
        String dateFormatted = date.substring(0,10);

        return dateFormatted;
    }

    // Our ViewHolder. It caches our TextViews
    static class ViewHolderItem {
        TextView headerTv;
        TextView sectionTv;
        TextView publishedTv;
    }
}
