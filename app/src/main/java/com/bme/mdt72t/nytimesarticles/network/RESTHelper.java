package com.bme.mdt72t.nytimesarticles.network;

import android.util.Log;

import com.bme.mdt72t.nytimesarticles.model.ArticleConverter;
import com.bme.mdt72t.nytimesarticles.model.original.JsonQueryPOJO;
import com.bme.mdt72t.nytimesarticles.network.NYTimesArticleAPI;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;
import com.bme.mdt72t.nytimesarticles.ui.main.PresenterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTHelper {

    private static final String TAG = "RESTHelper";

    NYTimesArticleAPI service;

    public RESTHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYTimesArticleAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NYTimesArticleAPI.class);
    }

    public void getArticle(final PresenterInterface presenter) {

        Call<JsonQueryPOJO> call = service.loadArticles("all-sections", "7");
        call.enqueue(new Callback<JsonQueryPOJO>() {

            @Override
            public void onResponse(Call<JsonQueryPOJO> call, Response<JsonQueryPOJO> response) {
                    JsonQueryPOJO jsonQueryPOJO = response.body();
                    if(presenter!=null)
                    presenter.gotContent(new ArticleConverter().JsonToArticle(jsonQueryPOJO));
                    else
                        Log.e(TAG, "No reference to presenter!");
            }

            @Override
            public void onFailure(Call<JsonQueryPOJO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
