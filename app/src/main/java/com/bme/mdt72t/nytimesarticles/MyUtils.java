package com.bme.mdt72t.nytimesarticles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.model.Result;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

class MyUtils {
    public static boolean checkFirstRun(Context context) {
        boolean isFirstRun = context.getSharedPreferences("run", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun)
            return true;
        return false;
    }

    public static ArticlesPOJO getDummyArticle() {
        ArticlesPOJO articlesPOJO = new ArticlesPOJO();
        List<Result> results = new ArrayList<Result>();

        Result result1 = new Result();
        result1.setTitle("Ausztrál pingvinek a sivatagban !");
        result1.setByline("by POCOK PÁL");
        result1.setUrl("http://valami.dx.am/");
        results.add(result1);

        Result result2 = new Result();
        result2.setTitle("Görlandi makákók megeszik a kenyeret ?");
        result2.setByline("by SZAVAS MAGDA");
        result2.setUrl("http://valami.dx.am/");
        results.add(result2);

        Result result3 = new Result();
        result3.setTitle("Osztrák orszarvúak tegnap a házban törtek és zúztak.");
        result3.setByline("by MACKÓ LACKÓ");
        result3.setUrl("http://valami.dx.am/");
        results.add(result3);

        articlesPOJO.setResults(results);
        return articlesPOJO;
    }

    public static ArticlesPOJO getLastArticles(Context context) {
        Gson gson = new Gson();
        String json = context.getSharedPreferences("articles", MODE_PRIVATE)
                .getString("lastArticles", "error");
        return gson.fromJson(json, ArticlesPOJO.class);
    }

    public static void setLastArticles(ArticlesPOJO articlesPOJO, Context context) {
        Gson gson = new Gson();
        String json = gson.toJson(articlesPOJO);
        context.getSharedPreferences("articles", MODE_PRIVATE)
                .edit()
                .putString("lastArticles", json)
                .apply();
        context.getSharedPreferences("run", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply();
    }

    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            return false;
        } else {
            if (info.isConnected()) {
                return true;
            } else {
                return false;
            }

        }
    }
}
