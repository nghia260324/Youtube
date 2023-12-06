package com.example.youtube.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context) {
        super(context, "DatabaseVideo", null, 2);
    }

    public void dataQuery(String command) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(command);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command_1 = "CREATE TABLE VIDEO(ID TEXT,TITLEVIDEO TEXT,THUMBNAILVIDEO TEXT," +
                "PATHVIDEO TEXT,THUMBNAILCHANNEL TEXT,TITLECHANNEL TEXT,VIEWS TEXT,TIME TEXT,SUB TEXT,LIKES TEXT,COMMENT TEXT )";
        db.execSQL(command_1);
        String command_2 = "INSERT INTO VIDEO VALUES('944c8d51-e41d-43f5-ab6b-21e0a660fb10'," +
                "'LÀ ANH - PHẠM LỊCH'," +
                "'https://i.ytimg.com/vi/6-LmZLjS5WI/hqdefault.jpg'," +
                "'https://www.youtube.com/embed/6-LmZLjS5WI'," +
                "'https://yt3.ggpht.com/rSriuiJlAqvztZkoMmggXHR_Kozy…t9e3QYT_57INZD0KpK8KEwQ=s88-c-k-c0x00ffffff-no-rj'," +
                "'Phạm Lịch'," +
                "'27M'," +
                "'2 tháng trước'," +
                "'56,9N'," +
                "'1,3N'," +
                "'75')";
        db.execSQL(command_2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS VIDEO");
            onCreate(db);
        }
    }
}
