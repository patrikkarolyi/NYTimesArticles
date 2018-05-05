package com.bme.mdt72t.nytimesarticles.ui.main.adapter;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bme.mdt72t.nytimesarticles.R;
import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private MainScreen mainScreen;
    private List<Article> articles;

    public ArticleAdapter(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.byline)
        TextView byLine;
        @BindView(R.id.img)
        CircleImageView cirlceImage;
        @BindView(R.id.nextsign)
        ImageView nextSign;
        @BindView(R.id.article_panel)
        LinearLayout articlePanel;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        final Article currentArticle = articles.get(position);

        String title = currentArticle.getTitle();
        holder.title.setText(title);

        String byLine = currentArticle.getByline() + "  " + currentArticle.getPublished_date();
        holder.byLine.setText(byLine);

        Picasso.get().load(currentArticle.getImgUrl())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.cirlceImage);

        holder.nextSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainScreen.loadArticleUrl(currentArticle.getUrl()))
                ObjectAnimator
                        .ofFloat(v, "translationX",
                                0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                        .setDuration(3000)
                        .start();
            }
        });
        holder.articlePanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainScreen.openDetailsActivity(currentArticle);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(articles == null)
            return 0;
        return articles.size();
    }

    public void setContent(List<Article> articles) {
        this.articles = articles;
        this.notifyDataSetChanged();
    }
}
