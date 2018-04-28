package com.bme.mdt72t.nytimesarticles.ui.adapter;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bme.mdt72t.nytimesarticles.R;
import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;
import com.bme.mdt72t.nytimesarticles.model.Result;
import com.bme.mdt72t.nytimesarticles.ui.main.MainScreen;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private MainScreen mainScreen;
    private ArticlesPOJO articlesPOJO;

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
        final Result currentResult = articlesPOJO.getResults().get(position);

        String title = currentResult.getTitle();
        holder.title.setText(title);

        String byLine = currentResult.getByline() + "  " + currentResult.getPublished_date();
        holder.byLine.setText(byLine);

        Picasso.get().load(getThumbnail(currentResult))
                .error(R.mipmap.ic_launcher_round)
                .into(holder.cirlceImage);

        holder.nextSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mainScreen.loadArticleUrl(currentResult.getUrl()))
                ObjectAnimator
                        .ofFloat(v, "translationX",
                                0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                        .setDuration(3000)
                        .start();
            }
        });
    }


    private String getThumbnail(Result currentResult) {
        String url = null;
        //check POJO for url step-by-step
        if (currentResult.getMediaList() != null)
            if (currentResult.getMediaList().get(0).getMetadata() != null)
                if (currentResult.getMediaList().get(0).getMetadata().get(0).getUrl() != null)
                    url = currentResult.getMediaList().get(0).getMetadata().get(0).getUrl();
        if (url == null)
            //if article has no image
            url = "https://static01.nyt.com/images/2018/04/14/world/14syriaattack-span/14syriaattack-span-square320.jpg";
        return url;
    }

    @Override
    public int getItemCount() {
        if(articlesPOJO == null)
            return 0;
        return articlesPOJO.getResults().size();
    }

    public void setContent(ArticlesPOJO articlesPOJO) {
        this.articlesPOJO = articlesPOJO;
        this.notifyDataSetChanged();
    }
}
