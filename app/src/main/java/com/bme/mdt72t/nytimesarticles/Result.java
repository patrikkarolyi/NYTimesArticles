package com.bme.mdt72t.nytimesarticles;


import com.google.gson.annotations.SerializedName;

class Result {
    @SerializedName("abstract")
    private String mAbstract;

    @SerializedName("published_date")
    private String published_date;

    @SerializedName("asset_id")
    private String asset_id;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("section")
    private String section;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("byline")
    private String byline;

    @SerializedName("source")
    private String source;

    @SerializedName("views")
    private String views;

    @SerializedName("column")
    private String column;

    @SerializedName("adx_keywords")
    private String adx_keywords;

    public String getAbstract() {
        return mAbstract;
    }

    public void setAbstract(String mAbstract) {
        this.mAbstract = mAbstract;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public void setAdx_keywords(String adx_keywords) {
        this.adx_keywords = adx_keywords;
    }


    @Override
    public String toString() {
        return "ClassPojo [abstract = " + mAbstract + ", published_date = " + published_date + ", asset_id = " + asset_id + ", type = " + type + ", url = " + url + ", section = " + section + ", id = " + id + ", title = " + title + ", byline = " + byline + ", source = " + source + ", views = " + views + ", column = " + column + ", adx_keywords = " + adx_keywords + "]";
    }
}
