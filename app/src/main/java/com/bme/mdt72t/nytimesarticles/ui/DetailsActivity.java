package com.bme.mdt72t.nytimesarticles.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bme.mdt72t.nytimesarticles.R;
import com.bme.mdt72t.nytimesarticles.model.Article;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.details_title)
    TextView titleTextView;
    @BindView(R.id.details_content)
    TextView contentTextView;
    @BindView(R.id.details_img)
    ImageView imageView;
    @BindView(R.id.details_date)
    TextView dateTextView;
    private Article article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String articleJson = intent.getStringExtra("Article");
        Gson gson = new Gson();
        article = gson.fromJson(articleJson, Article.class);

        titleTextView.setText(article.getTitle());
        contentTextView.setText(article.getContent());
        dateTextView.setText(article.getPublished_date());

        Picasso.get().load(article.getImgUrl())
                .error(R.mipmap.ic_launcher_round)
                .into(imageView);



    }
}
