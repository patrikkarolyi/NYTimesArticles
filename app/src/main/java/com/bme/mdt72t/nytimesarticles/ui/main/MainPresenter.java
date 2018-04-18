package com.bme.mdt72t.nytimesarticles.ui.main;

import com.bme.mdt72t.nytimesarticles.interactor.ArticlesInteractor;
import com.bme.mdt72t.nytimesarticles.interactor.LocalInteractor;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    private static MainPresenter instance = null;

    private MainPresenter() {
    }

    public static MainPresenter getInstance() {
        if (instance == null) {
            instance = new MainPresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void getArticles() {
        new ArticlesInteractor().getArticle(screen);
    }


    public boolean isInternetAvailable() {
        return LocalInteractor.isInternetAvailable();
    }

    public ArticlesPOJO getInitContent() {
        if (LocalInteractor.checkFirstRun())
            return LocalInteractor.getDummyArticle();
        else
            return LocalInteractor.getLastArticles();
    }
}