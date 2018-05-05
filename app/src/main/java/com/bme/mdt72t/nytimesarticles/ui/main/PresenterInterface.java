package com.bme.mdt72t.nytimesarticles.ui.main;

import com.bme.mdt72t.nytimesarticles.model.Article;

import java.util.List;

public interface PresenterInterface {
    public void gotContent(List<Article> articles);
}
