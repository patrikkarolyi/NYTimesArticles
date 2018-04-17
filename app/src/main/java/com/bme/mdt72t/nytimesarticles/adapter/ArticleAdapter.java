package com.bme.mdt72t.nytimesarticles.adapter;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context context;
    private ArticlesPOJO articlesPOJO;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView byLine;
        CircleImageView cirlceImage;
        ImageView nextSign;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            byLine = itemView.findViewById(R.id.byline);
            cirlceImage = itemView.findViewById(R.id.img);
            nextSign = itemView.findViewById(R.id.nextsign);
        }
    }

    public ArticleAdapter(ArticlesPOJO articlesPOJO, Context context) {
        this.articlesPOJO = articlesPOJO;
        this.context = context;
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

        holder.title.setText(currentResult.getTitle());
        holder.byLine.setText(currentResult.getByline() + "  " +currentResult.getPublished_date());
        Picasso.get().load(getThumbnail(currentResult))
                .error(R.mipmap.ic_launcher_round)
                .into(holder.cirlceImage);

        holder.nextSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(currentResult.getUrl()));
                    context.startActivity(i);
                } catch (ActivityNotFoundException e) {
                    // Chrome is not installed
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentResult.getUrl()));
                    context.startActivity(i);
                }
            }
        });

    }

    private String getThumbnail(Result currentResult) {
       String url = null;
        if(currentResult.getMediaList() != null)
            if(currentResult.getMediaList().get(0).getMetadata() != null)
                if(currentResult.getMediaList().get(0).getMetadata().get(0).getUrl() != null)
                    url = currentResult.getMediaList().get(0).getMetadata().get(0).getUrl();
        if(url == null)
            url = "https://static01.nyt.com/images/2018/04/14/world/14syriaattack-span/14syriaattack-span-square320.jpg";
        return url;
    }

    @Override
    public int getItemCount() {
        return articlesPOJO.getResults().size();
    }


}
