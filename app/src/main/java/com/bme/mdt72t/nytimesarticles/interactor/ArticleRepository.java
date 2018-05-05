package com.bme.mdt72t.nytimesarticles.interactor;

import android.content.Context;
import android.os.AsyncTask;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.model.room.ArticleDao;
import com.bme.mdt72t.nytimesarticles.model.room.ArticleDatabase;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;

import java.util.List;

public class ArticleRepository {

    private ArticleDao articleDao;



    public ArticleRepository(Context context) {
        ArticleDatabase articleDatabase = ArticleDatabase.getDatabase(context);
        articleDao = articleDatabase.articleDao();
    }

    public void getArticles(MainScreen mainScreen) {
        new getAsyncTask(articleDao,mainScreen).execute();
    }

    public void insert(List<Article> article) {
        new insertAsyncTask(articleDao).execute(article);
    }


    private static class insertAsyncTask extends AsyncTask<List<Article>, Void, Void> {

        private ArticleDao asyncTaskDao;

        insertAsyncTask(ArticleDao articleDao) {
            asyncTaskDao = articleDao;
        }

        @Override
        protected Void doInBackground(final List<Article>... params) {
            asyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
    private static class getAsyncTask extends AsyncTask<Void, Void, Void> {

        private ArticleDao asyncTaskDao;
        private MainScreen mainScreen;
        private List<Article> articles;

        getAsyncTask(ArticleDao articleDao, MainScreen mainScreen) {
            this.asyncTaskDao = articleDao;
            this.mainScreen = mainScreen;
        }

        @Override
        protected  Void doInBackground(Void... voids) {
            articles  = asyncTaskDao.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mainScreen.showArticles(articles,false);
        }

    }

}
