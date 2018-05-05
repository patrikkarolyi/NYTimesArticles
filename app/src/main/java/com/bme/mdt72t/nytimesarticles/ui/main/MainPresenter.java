package com.bme.mdt72t.nytimesarticles.ui.main;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bme.mdt72t.nytimesarticles.interactor.RESTHelper;
import com.bme.mdt72t.nytimesarticles.interactor.Interactor;
import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.DetailsActivity;
import com.bme.mdt72t.nytimesarticles.ui.Presenter;
import com.google.gson.Gson;

import java.util.List;

public class MainPresenter extends Presenter<MainScreen> implements PresenterInterface{

    private Interactor interactor;
    private Context context;

    MainPresenter(Application application,Context context) {
        this.interactor = new Interactor(application);
        this.context = context;

    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public boolean loadArticleUrl(String url) {
        if (interactor.isInternetAvailable()) {
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

    public void openDetailsActivity(Article currentArticle) {
        Intent i = new Intent(context, DetailsActivity.class);
        Gson gson = new Gson();
        i.putExtra("Article", gson.toJson(currentArticle));
        context.startActivity(i);

    }

    public void getContent() {
        if (interactor.isInternetAvailable()) {
            interactor.getRESTArticles(this);
        }
        else if(interactor.isFirstRun()){
            screen.showNoConnectionDialogWindow();
            screen.hideSwipeRefreshLayout();
        }
        else {
            screen.showSnackbar();
            interactor.getLocalArticles(this);
        }
    }

    public void gotContent(List<Article> articles) {
        //TODO android diffUtil
        screen.setArticles(articles);
        screen.hideSwipeRefreshLayout();
        if(interactor.isInternetAvailable());
        interactor.setLocalArticles(articles);
    }
}