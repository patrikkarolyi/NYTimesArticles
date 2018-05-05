package com.bme.mdt72t.nytimesarticles.model;

import android.util.Log;

import com.bme.mdt72t.nytimesarticles.model.original.JsonQueryPOJO;
import com.bme.mdt72t.nytimesarticles.model.original.Result;

import java.util.ArrayList;

public class ArticleConverter {
    private static final String TAG = "AConverter";

    public ArrayList<Article> JsonToArticle(JsonQueryPOJO jsonQueryPOJO){
        int size = jsonQueryPOJO.getResults().size();
        ArrayList<Article> result = new ArrayList<>(size);

        for(int i=0; i<size;i++){
            Article article= new Article();
            Result temp = jsonQueryPOJO.getResults().get(i);

            article.setId(Long.getLong(temp.getId()));
            article.setTopic(temp.getSection());
            article.setTitle(temp.getTitle());
            article.setContent(temp.getAbstract());
            article.setPublished_date(temp.getPublished_date());
            article.setByline(temp.getByline());
            article.setUrl(temp.getUrl());
            article.setImgUrl(getThumbnail(temp));

            result.add(article);
        }
        return result;
    }

    private String getThumbnail(Result currentResult) {
        String url = null;
        //check POJO for url step-by-step
        if (currentResult.getMediaList() != null)
            if (currentResult.getMediaList().get(0).getMetadata() != null)
                url = currentResult.getMediaList().get(0).getMetadata().get(0).getUrl();
        if (url == null)
            //if article has no image
            url = "https://static01.nyt.com/images/2018/04/14/world/14syriaattack-span/14syriaattack-span-square320.jpg";
        return url;
    }
}
