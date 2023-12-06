package com.example.youtube.Object;

public class Video {
    private String id;
    private String title_video;
    private String thumbnail_video;
    private String path_video;
    private String thumbnail_channel;
    private String title_channel;
    private String view;
    private String time;
    private String sub;
    private String like;
    private String comment;

    public Video() {}
    public Video(String id, String title_video, String thumbnail_video, String path_video,
                 String thumbnail_channel, String title_channel, String view, String time,
                 String sub, String like, String comment) {
        this.id = id;
        this.title_video = title_video;
        this.thumbnail_video = thumbnail_video;
        this.path_video = path_video;
        this.thumbnail_channel = thumbnail_channel;
        this.title_channel = title_channel;
        this.view = view;
        this.time = time;
        this.sub = sub;
        this.like = like;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle_video() {
        return title_video;
    }

    public void setTitle_video(String title_video) {
        this.title_video = title_video;
    }

    public String getThumbnail_video() {
        return thumbnail_video;
    }

    public void setThumbnail_video(String thumbnail_video) {
        this.thumbnail_video = thumbnail_video;
    }

    public String getPath_video() {
        return path_video;
    }

    public void setPath_video(String path_video) {
        this.path_video = path_video;
    }

    public String getThumbnail_channel() {
        return thumbnail_channel;
    }

    public void setThumbnail_channel(String thumbnail_channel) {
        this.thumbnail_channel = thumbnail_channel;
    }

    public String getTitle_channel() {
        return title_channel;
    }

    public void setTitle_channel(String title_channel) {
        this.title_channel = title_channel;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
