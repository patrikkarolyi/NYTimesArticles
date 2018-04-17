package com.bme.mdt72t.nytimesarticles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bme.mdt72t.nytimesarticles.adapter.ArticleAdapter;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.model.Result;
import com.bme.mdt72t.nytimesarticles.network.ArticleAsker;
import com.bme.mdt72t.nytimesarticles.network.GetArticlesAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ArticleAsker {

    private static final String TAG = "MainActivity";

    //UI vars
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        new GetArticlesAPI().getArticle(this);

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        articleAdapter = new ArticleAdapter(getDummyArticle(), this);
        recyclerView.setAdapter(articleAdapter);

    }

    private ArticlesPOJO getDummyArticle() {
        ArticlesPOJO articlesPOJO = new ArticlesPOJO();
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
        return articlesPOJO;
    }


    @Override
    public void giveArticles(ArticlesPOJO articlesPOJO) {
        recyclerView.setAdapter(new ArticleAdapter(articlesPOJO,this));
        Toast.makeText(this,"Articles updated!",Toast.LENGTH_SHORT);
    }
}
