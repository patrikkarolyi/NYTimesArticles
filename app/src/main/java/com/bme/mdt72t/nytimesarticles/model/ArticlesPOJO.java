package com.bme.mdt72t.nytimesarticles.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ArticlesPOJO {

    @SerializedName("results")
    private List<Result> results;

    @SerializedName("status")
    private String status;


    @SerializedName("num_results")
    private int num_results;

    @SerializedName("copyright")
    private String copyright;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public String toString() {
        return "ClassPojo [results = " + results + ", status = " + status + ", num_results = " + num_results + ", copyright = " + copyright + "]";
    }
}
