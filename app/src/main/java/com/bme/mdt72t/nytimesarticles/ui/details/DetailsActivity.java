package com.bme.mdt72t.nytimesarticles.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bme.mdt72t.nytimesarticles.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    @BindView(R.id.details_title)
    TextView titleTextView;
    @BindView(R.id.details_content)
    TextView contentTextView;
    @BindView(R.id.details_img)
    ImageView imageView;
    @BindView(R.id.details_date)
    TextView dateTextView;

    private DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        detailsPresenter = new DetailsPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        detailsPresenter.attachScreen(this);
        Intent intent = getIntent();
        detailsPresenter.getContent(intent.getStringExtra("Article"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsPresenter.detachScreen();
    }

    @Override
    public void setTitleTextView(String title){
        titleTextView.setText(title);
    }

    @Override
    public void setContentTextView(String content) {
        contentTextView.setText(content);
    }

    @Override
    public void setDateTextView(String published_date) {
        dateTextView.setText(published_date);
    }

    @Override
    public void setImageViewUrl(String imgUrl) {
        Picasso.get().load(imgUrl)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView);
    }

}
