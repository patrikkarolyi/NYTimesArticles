package com.bme.mdt72t.nytimesarticles.ui.details;

public interface DetailsScreen {
    void setTitleTextView(String title);

    void setContentTextView(String content);

    void setDateTextView(String published_date);

    void setImageViewUrl(String imgUrl);

    void setButtonViewUrl();
}
