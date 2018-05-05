package com.bme.mdt72t.nytimesarticles.interactor;

import android.util.Log;

import com.bme.mdt72t.nytimesarticles.model.ArticleConverter;
import com.bme.mdt72t.nytimesarticles.model.original.JsonQueryPOJO;
import com.bme.mdt72t.nytimesarticles.network.NYTimesArticleAPI;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InternetInteractor {

    private static final String TAG = "InternetInteractor";

    NYTimesArticleAPI service;

    public InternetInteractor() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYTimesArticleAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NYTimesArticleAPI.class);
    }

    public void getArticle(final MainScreen screen) {

        Call<JsonQueryPOJO> call = service.loadArticles("all-sections", "7");
        call.enqueue(new Callback<JsonQueryPOJO>() {

            @Override
            public void onResponse(Call<JsonQueryPOJO> call, Response<JsonQueryPOJO> response) {
                    JsonQueryPOJO jsonQueryPOJO = response.body();
                    screen.showArticles(new ArticleConverter().JsonToArticle(jsonQueryPOJO),true);
            }

            @Override
            public void onFailure(Call<JsonQueryPOJO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
