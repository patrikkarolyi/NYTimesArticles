package com.bme.mdt72t.nytimesarticles;

import android.content.Context;
import android.content.SharedPreferences;

import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.model.Result;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

class MyUtils {
    public static boolean checkFirstRun(Context context) {
        boolean isFirstRun = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
            return true;
        }
        return false;
    }

    public static ArticlesPOJO getDummyArticle() {
        ArticlesPOJO articlesPOJO = new ArticlesPOJO();
        List<Result> results = new ArrayList<Result>();

        Result result1 = new Result();
        result1.setTitle("Ausztrál pingvinek");
        result1.setByline("Pocok Pál");
        result1.setUrl("http://valami.dx.am/");
        results.add(result1);

        Result result2 = new Result();
        result2.setTitle("Görlandi makákók");
        result2.setByline("Szarvas Magda");
        result2.setUrl("http://valami.dx.am/");
        results.add(result2);

        Result result3 = new Result();
        result3.setTitle("Osztrák orszarvúak");
        result3.setByline("Mackó Lackó");
        result3.setUrl("http://valami.dx.am/");
        results.add(result3);

        articlesPOJO.setResults(results);
        return articlesPOJO;
    }

    public static ArticlesPOJO getLastArticles() {
        ArticlesPOJO articlesPOJO = new ArticlesPOJO();
        List<Result> results = new ArrayList<Result>();

        Result result1 = new Result();
        result1.setTitle("Második pingvinek");
        result1.setByline("Pocok Pál");
        result1.setUrl("http://valami.dx.am/");
        results.add(result1);

        Result result2 = new Result();
        result2.setTitle("Második makákók");
        result2.setByline("Szarvas Magda");
        result2.setUrl("http://valami.dx.am/");
        results.add(result2);

        Result result3 = new Result();
        result3.setTitle("Második orszarvúak");
        result3.setByline("Mackó Lackó");
        result3.setUrl("http://valami.dx.am/");
        results.add(result3);

        articlesPOJO.setResults(results);
        return articlesPOJO;
    }

    public static void setLastArticles(ArticlesPOJO articlesPOJO) {

    }
}
