package com.bme.mdt72t.nytimesarticles.ui.main;

import android.content.Context;
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
    private static Context context;
    private MainPresenter mainPresenter;

    // UI vars
    @BindView(R.id.main_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.main_coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    Snackbar snackbar;


    // ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        mainPresenter = MainPresenter.getInstance();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getArticlesFromInternet();
            }
        });
    }

    // ONSTART
    @Override
    protected void onStart() {
        super.onStart();
        MainPresenter.getInstance().attachScreen(this);
        initRecyclerView();
        getArticlesFromInternet();
    }

    // ONSTOP
    @Override
    protected void onStop() {
        super.onStop();
        MainPresenter.getInstance().detachScreen();
    }

    private void getArticlesFromInternet() {
        if (mainPresenter.isInternetAvailable()) {

            mainPresenter.getArticles();
            if (snackbar != null)
                snackbar.dismiss();
        } else {
            swipeRefreshLayout.setRefreshing(false);
            snackbar = Snackbar
                    .make(coordinatorLayout, R.string.main_snackbar_no_internet, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.main_snackbar_retry, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Retry to get content from internet
                            getArticlesFromInternet();
                        }
                    });
            snackbar.show();
        }
    }


    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ArticleAdapter(mainPresenter.getInitContent(), this));
    }

    //Share base context with LocalInteractor
    public static Context getContextOfApplication() {
        return context;
    }

    @Override
    public void showArticles(ArticlesPOJO articlesPOJO) {
        recyclerView.setAdapter(new ArticleAdapter(articlesPOJO, this));
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, R.string.main_articles_updated, Toast.LENGTH_SHORT).show();
    }
}
