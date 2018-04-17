package com.bme.mdt72t.nytimesarticles.network;

import android.util.Log;

import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetArticlesAPI {

    private static final String TAG = "GetArticlesAPI";

    NYTimesArticleAPI service;

    public GetArticlesAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYTimesArticleAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NYTimesArticleAPI.class);
    }

    public void getArticle(final ArticleAsker aa) {

        Call<ArticlesPOJO> call = service.loadCards("all-sections", "7");
        call.enqueue(new Callback<ArticlesPOJO>() {

            @Override
            public void onResponse(Call<ArticlesPOJO> call, Response<ArticlesPOJO> response) {
                    ArticlesPOJO articlesPOJO  = response.body();
                    aa.giveArticles(articlesPOJO);
            }

            @Override
            public void onFailure(Call<ArticlesPOJO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
