package com.bme.mdt72t.nytimesarticles.ui.main;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bme.mdt72t.nytimesarticles.interactor.InternetInteractor;
import com.bme.mdt72t.nytimesarticles.interactor.LocalInteractor;
import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.DetailsActivity;
import com.bme.mdt72t.nytimesarticles.ui.Presenter;
import com.google.gson.Gson;

import java.util.List;

public class MainPresenter extends Presenter<MainScreen> {

    private Context context;
    private LocalInteractor localInteractor;
    private InternetInteractor internetInteractor;

    MainPresenter(Context context) {
        this.context = context;
        localInteractor = new LocalInteractor(context);
        internetInteractor = new InternetInteractor();

    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }


    public void getInitContent() {
        if(localInteractor.checkFirstRun())
            if(!localInteractor.isInternetAvailable())
                screen.showNoConnectionDialogWindow();
        else
            localInteractor.getLastArticles(screen);
    }


    public void getArticlesFromInternet() {
        if (localInteractor.isInternetAvailable()) {
            internetInteractor.getArticle(screen);
            screen.hideSnackbar();
        } else {
            screen.hideSwipeRefreshLayout();
            screen.showSnackbar();
        }
    }


    public boolean loadArticleUrl(String url) {
        if (localInteractor.isInternetAvailable()) {
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
            return true;
        } else
            return false;
    }


    public void setLastArticles(List<Article> articles) {
        localInteractor.setLastArticles(articles);
    }

    public void openDetailsActivity(Article currentArticle) {
        Intent i = new Intent(context, DetailsActivity.class);
        Gson gson = new Gson();
        i.putExtra("Article", gson.toJson(currentArticle));
        context.startActivity(i);

    }
}