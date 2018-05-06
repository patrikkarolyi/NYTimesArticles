package com.bme.mdt72t.nytimesarticles.model.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bme.mdt72t.nytimesarticles.model.Article;

import java.util.List;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE id IN (:articleIds)")
    List<Article> loadAllByIds(int[] articleIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Article> articles);

    @Query("DELETE FROM article")
    void deleteAll();

    @Delete
    void delete(Article article);
}
