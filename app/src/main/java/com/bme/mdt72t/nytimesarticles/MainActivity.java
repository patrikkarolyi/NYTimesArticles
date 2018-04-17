package com.bme.mdt72t.nytimesarticles;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    @BindView(R.id.main_coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        getArticlesFromInternet();
    }

    private void getArticlesFromInternet(){
        if(MyUtils.isInternetAvailable(this))
            new GetArticlesAPI().getArticle(this);
        else{
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getArticlesFromInternet();
                        }
                    });
            snackbar.show();
        }
    }



    private void initRecyclerView() {
        ArticlesPOJO articlesData;

        if(MyUtils.checkFirstRun(this))
            articlesData = MyUtils.getDummyArticle();
        else
            articlesData = MyUtils.getLastArticles(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ArticleAdapter(articlesData, this));

    }

    @Override
    public void giveArticles(ArticlesPOJO articlesPOJO) {
        recyclerView.setAdapter(new ArticleAdapter(articlesPOJO,this));
        Toast.makeText(this,"Articles updated!",Toast.LENGTH_SHORT);
        MyUtils.setLastArticles(articlesPOJO,this);
    }
}
