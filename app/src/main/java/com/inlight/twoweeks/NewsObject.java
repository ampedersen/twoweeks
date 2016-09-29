package com.inlight.twoweeks;

/**
 * Created by anderspedersen on 28/09/16.
 */

public class NewsObject {

    // Header of the News Object
    private String mHeader;

    // Section of the News Object
    private String mSection;

    // Publish date of the News Object
    private String mPublish;

    // URL on Web
    private String mUrl;

    public NewsObject (String header, String section, String publish, String url) {
        mHeader = header;
        mSection = section;
        mPublish = publish;
        mUrl = url;
    }

    public String getHeader() {
        return mHeader;
    }

    public String getSection() {
        return mSection;
    }

    public String getPublish() {
        return mPublish;
    }

    public String getUrl() {
        return mUrl;
    }

}

