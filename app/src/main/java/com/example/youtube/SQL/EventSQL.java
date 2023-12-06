package com.example.youtube.SQL;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.youtube.Object.Video;

import java.util.ArrayList;

public class EventSQL{

    final Context context;
    DataBase dataBase;

    public EventSQL(Context context) {
        this.context = context;
        dataBase = new DataBase(context);
    }

    public ArrayList<Video> getAllVideo(String tablename) {
        ArrayList<Video> lstVideo  = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dataBase.getReadableDatabase();
        sqLiteDatabase.beginTransaction();

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+tablename+"",null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    lstVideo.add(new Video(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10)));
                } while (cursor.moveToNext());
                sqLiteDatabase.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e(TAG,"getListVideo: " + e);
        } finally {
            sqLiteDatabase.endTransaction();
        }
        return lstVideo;
    }

    public boolean addVideo(Video video,String table_name) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",video.getId());
        contentValues.put("TITLEVIDEO",video.getTitle_video());
        contentValues.put("THUMBNAILVIDEO",video.getThumbnail_video());
        contentValues.put("PATHVIDEO",video.getPath_video());
        contentValues.put("THUMBNAILCHANNEL",video.getThumbnail_channel());
        contentValues.put("TITLECHANNEL",video.getTitle_channel());
        contentValues.put("VIEWS",video.getView());
        contentValues.put("TIME",video.getTime());
        contentValues.put("SUB",video.getSub());
        contentValues.put("LIKES",video.getLike());
        contentValues.put("COMMENT",video.getComment());

        long check = sqLiteDatabase.insert(table_name,null,contentValues);
        return check != -1;
    }
    public boolean updateVideo(Video video,String id,String tableName) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",video.getId());
        contentValues.put("TITLEVIDEO",video.getTitle_video());
        contentValues.put("THUMBNAILVIDEO",video.getThumbnail_video());
        contentValues.put("PATHVIDEO",video.getPath_video());
        contentValues.put("THUMBNAILCHANNEL",video.getThumbnail_channel());
        contentValues.put("TITLECHANNEL",video.getTitle_channel());
        contentValues.put("VIEWS",video.getView());
        contentValues.put("TIME",video.getTime());
        contentValues.put("SUB",video.getSub());
        contentValues.put("LIKES",video.getLike());
        contentValues.put("COMMENT",video.getComment());

        long check = sqLiteDatabase.update(tableName,contentValues,"ID = ?",new String[]{id});
        return check != -1;
    }

    public boolean deleteVideo(String id) {
        SQLiteDatabase sqLiteDatabase = dataBase.getWritableDatabase();

        long check = sqLiteDatabase.delete("Video","ID = ?",new String[]{id});
        return check != -1;
    }
}
