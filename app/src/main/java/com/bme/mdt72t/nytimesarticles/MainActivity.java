package com.bme.mdt72t.nytimesarticles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.network.NYTimesArticleService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //UI vars
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //getArticles();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter();


    }




    private void getArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYTimesArticleService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NYTimesArticleService service = retrofit.create(NYTimesArticleService.class);
        Call<ArticlesPOJO> call = service.loadCards("all-sections", "7");
        call.enqueue(new Callback<ArticlesPOJO>() {

            @Override
            public void onResponse(Call<ArticlesPOJO> call, Response<ArticlesPOJO> response) {
                ArticlesPOJO articlePOJO = response.body();

            }

            @Override
            public void onFailure(Call<ArticlesPOJO> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Error during query!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
