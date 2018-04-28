package com.bme.mdt72t.nytimesarticles.ui.main;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bme.mdt72t.nytimesarticles.interactor.InternetInteractor;
import com.bme.mdt72t.nytimesarticles.interactor.LocalInteractor;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    private Context context;

    MainPresenter(Context context) {
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

    public ArticlesPOJO getInitContent() {
        if (LocalInteractor.checkFirstRun(context))
            return LocalInteractor.getDummyArticle();
        else
            return LocalInteractor.getLastArticles(context);
    }

    public void getArticlesFromInternet() {
        if (LocalInteractor.isInternetAvailable(context)) {
            new InternetInteractor().getArticle(screen);
            screen.hideSnackbar();
        } else {
            screen.hideSwipeRefreshLayout();
            screen.showSnackbar();
        }
    }

    public boolean loadArticleUrl(String url) {
        if (LocalInteractor.isInternetAvailable(context)) {
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
        }
        else
            return false;
    }

    public void setLastArticles(ArticlesPOJO articlesPOJO) {
        LocalInteractor.setLastArticles(articlesPOJO,context);
    }
}