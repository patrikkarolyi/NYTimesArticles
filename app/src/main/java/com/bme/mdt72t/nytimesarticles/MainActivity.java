package com.bme.mdt72t.nytimesarticles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bme.mdt72t.nytimesarticles.adapter.ArticleAdapter;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.model.Result;
import com.bme.mdt72t.nytimesarticles.network.NYTimesArticleService;

import java.util.ArrayList;
import java.util.List;

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
    ArticleAdapter articleAdapter;
    ArticlesPOJO articlesPOJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getArticles();
        //TODO first run POJO


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        articlesPOJO = new ArticlesPOJO();
        List<Result> results = new ArrayList<Result>();

        Result result1 = new Result();
        result1.setTitle("Ausztrál pingvinek");
        result1.setByline("Pocok Pál");
        result1.setUrl("http://valami.dx.am/");
        results.add(result1);

        Result result2 = new Result();
        result2.setTitle("Görlandi makákók");
        result2.setByline("Prémes Feri");
        result2.setUrl("http://valami.dx.am/");
        results.add(result2);

        Result result3 = new Result();
        result3.setTitle("Osztrák orszarvúak");
        result3.setByline("Mackó Lackó");
        result3.setUrl("http://valami.dx.am/");
        results.add(result3);


        articlesPOJO.setResults(results);
        articleAdapter= new ArticleAdapter(articlesPOJO,this);
        recyclerView.setAdapter(articleAdapter);

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
                articlesPOJO = response.body();
                recyclerView.setAdapter(new ArticleAdapter(articlesPOJO,getApplicationContext()));
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
