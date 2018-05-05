package com.bme.mdt72t.nytimesarticles.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bme.mdt72t.nytimesarticles.model.Article;


@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();

    private static ArticleDatabase INSTANCE;

    public static ArticleDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), ArticleDatabase.class, "article_db")
                            .build();
        }
        return INSTANCE;
    }
}