package com.bme.mdt72t.nytimesarticles.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Article {

    @PrimaryKey
    private Long id;
    @ColumnInfo(name = "topic")
    private String topic;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "byline")
    private String byline;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "date")
    private String published_date;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "imgurl")
    private String imgUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
