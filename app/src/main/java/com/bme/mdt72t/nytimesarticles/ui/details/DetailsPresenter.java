package com.bme.mdt72t.nytimesarticles.ui.details;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.Presenter;
import com.google.gson.Gson;


public class DetailsPresenter extends Presenter<DetailsScreen> {

    private Article article;

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }


    public void getContent(String articleJson) {

        Gson gson = new Gson();
        article = gson.fromJson(articleJson, Article.class);

        screen.setTitleTextView(article.getTitle());
        screen.setContentTextView(article.getContent());
        screen.setDateTextView(article.getPublished_date());
        screen.setImageViewUrl(article.getImgUrl());
    }
}
