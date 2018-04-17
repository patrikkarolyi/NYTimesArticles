package com.bme.mdt72t.nytimesarticles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bme.mdt72t.nytimesarticles.adapter.ArticleAdapter;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.network.ArticleAsker;
import com.bme.mdt72t.nytimesarticles.network.GetArticlesAPI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ArticleAsker {

    private static final String TAG = "MainActivity";

    //UI vars
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        new GetArticlesAPI().getArticle(this);
    }

    private void initRecyclerView() {
        ArticlesPOJO articlesData;

        if(MyUtils.checkFirstRun(this))
            articlesData = MyUtils.getDummyArticle();
        else
            articlesData = MyUtils.getLastArticles();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ArticleAdapter(articlesData, this));

    }

    @Override
    public void giveArticles(ArticlesPOJO articlesPOJO) {
        recyclerView.setAdapter(new ArticleAdapter(articlesPOJO,this));
        Toast.makeText(this,"Articles updated!",Toast.LENGTH_SHORT);
        MyUtils.setLastArticles(articlesPOJO);
    }
}
