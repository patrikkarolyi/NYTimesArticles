package com.bme.mdt72t.nytimesarticles.ui.main;

import com.bme.mdt72t.nytimesarticles.model.Article;

import java.util.List;

public interface MainScreen {
    void setArticles(List<Article> articles);

    void showSnackbar();

    void hideSnackbar();

    void showSwipeRefreshLayout();

    void hideSwipeRefreshLayout();

    boolean loadArticleUrl(String url);

    void showNoConnectionDialogWindow();

    void openDetailsActivity(Article currentArticle);
}