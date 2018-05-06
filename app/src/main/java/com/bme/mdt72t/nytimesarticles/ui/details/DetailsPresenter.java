package com.bme.mdt72t.nytimesarticles.ui.details;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.Presenter;
import com.google.gson.Gson;


public class DetailsPresenter extends Presenter<DetailsScreen> {

    private Context context;
    private Article article;

    public DetailsPresenter(Context context) {
        this.context = context;
    }

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
        screen.setButtonViewUrl();
    }

    public void loadUrl() {
        String url = article.getUrl();
        try {
            // Start Chrome
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString(
                    "com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(i);
        }
    }
}
