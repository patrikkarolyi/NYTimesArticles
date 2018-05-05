package com.bme.mdt72t.nytimesarticles.interactor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.model.ArticleConverter;
import com.bme.mdt72t.nytimesarticles.model.original.JsonQueryPOJO;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LocalInteractor {

    private Context context;
    private ArticleRepository articleRepository;

    public LocalInteractor(Context context){
        this.context = context;
        this.articleRepository= new ArticleRepository(context);
    }


    public boolean checkFirstRun() {
        return context.getSharedPreferences("run", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
    }

    public void getLastArticles(MainScreen mainScreen) {
        articleRepository.getArticles(mainScreen);
    }

    public void setLastArticles(List<Article> articles) {
        articleRepository.insert(articles);
        context.getSharedPreferences("run", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply();
    }

    public boolean isInternetAvailable() {
        NetworkInfo info = ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info == null) {
            return false;
        } else {
            return info.isConnected();
        }
    }
}
