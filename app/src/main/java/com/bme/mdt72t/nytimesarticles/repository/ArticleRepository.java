package com.bme.mdt72t.nytimesarticles.repository;

import android.app.Application;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.model.room.ArticleDao;
import com.bme.mdt72t.nytimesarticles.model.room.ArticleDatabase;

import java.util.List;

public class ArticleRepository {

    private ArticleDao articleDao;

    public ArticleRepository(Application application) {

        ArticleDatabase articleDatabase = ArticleDatabase.getDatabase(application);
        articleDao = articleDatabase.articleDao();
    }

    List<Article> getAll() {
        return articleDao.getAll();
    }

    void setAll(List<Article> article) {
        articleDao.deleteAll();
        articleDao.insertAll(article);
    }
}
