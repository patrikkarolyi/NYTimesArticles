package com.bme.mdt72t.nytimesarticles.interactor.repository;

import android.os.AsyncTask;
import android.util.Log;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.main.PresenterInterface;

import java.util.List;

public class GetLocalArticlesTask extends AsyncTask {

    private static final String TAG = "GetLocalArticlesTask";
    private PresenterInterface presenter;
    private List<Article> articles;
    private ArticleRepository articleRepository;

    public GetLocalArticlesTask(PresenterInterface presenter, ArticleRepository articleRepository){
        this.presenter = presenter ;
        this.articleRepository = articleRepository;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        articles = articleRepository.getAll();
        Log.i(TAG, "DOINBACKGROUND");
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.i(TAG, "ONPOSTEXECUTE");
        if(presenter!=null)
            presenter.gotContent(articles);
        else
            Log.e(TAG, "No reference to presenter!");

    }
}
