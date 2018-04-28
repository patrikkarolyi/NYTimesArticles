package com.bme.mdt72t.nytimesarticles.ui.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bme.mdt72t.nytimesarticles.R;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.ui.adapter.ArticleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainScreen {

    private static final String TAG = "MainActivity";
    private MainPresenter mainPresenter;

    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.main_coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;
    private ArticleAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                mainPresenter.getArticlesFromInternet();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
        initRecyclerView();
        mainPresenter.getArticlesFromInternet();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new ArticleAdapter( this);
        mainAdapter.setContent(mainPresenter.getInitContent());
        recyclerView.setAdapter(mainAdapter);

    }

    @Override
    public void showArticles(ArticlesPOJO articlesPOJO) {
        mainAdapter.setContent(articlesPOJO);
        swipeRefreshLayout.setRefreshing(false);
        mainPresenter.setLastArticles(articlesPOJO);
        Toast.makeText(this, R.string.main_articles_updated, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideSnackbar() {
        if (snackbar != null)
            snackbar.dismiss();
    }

    @Override
    public void hideSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean loadArticleUrl(String url) {
        return mainPresenter.loadArticleUrl(url);
    }

    @Override
    public void showSnackbar() {
        snackbar = Snackbar
                .make(coordinatorLayout, R.string.main_snackbar_no_internet, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.main_snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Retry to get content from internet
                        mainPresenter.getArticlesFromInternet();
                    }
                });
        snackbar.show();
    }
}
