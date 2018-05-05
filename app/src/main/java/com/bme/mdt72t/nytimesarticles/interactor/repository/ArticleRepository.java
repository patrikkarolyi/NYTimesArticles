package com.bme.mdt72t.nytimesarticles.interactor.repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.model.room.ArticleDao;
import com.bme.mdt72t.nytimesarticles.model.room.ArticleDatabase;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;

import java.util.List;

public class ArticleRepository {

    private ArticleDao articleDao;

    public ArticleRepository(Application application) {

        ArticleDatabase articleDatabase = ArticleDatabase.getDatabase(application);
        articleDao = articleDatabase.articleDao();
    }

    public List<Article> getAll() {
        return articleDao.getAll();
    }

    public void insert(List<Article> article) {
        articleDao.insertAll(article);
    }
}
