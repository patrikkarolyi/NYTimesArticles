package com.bme.mdt72t.nytimesarticles.interactor.repository;

import android.os.AsyncTask;

import com.bme.mdt72t.nytimesarticles.model.Article;

import java.util.List;


public class SetLocalArticlesTask extends AsyncTask {

    private List<Article> articles;
    private ArticleRepository articleRepository;

    public SetLocalArticlesTask(List<Article> articles, ArticleRepository articleRepository) {
        this.articles = articles;
        this.articleRepository = articleRepository;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        articleRepository.insert(articles);
        return null;
    }
}