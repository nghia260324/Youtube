package com.example.youtube;

import com.example.youtube.Adapter.RecyclerViewAdapterAll;
import com.example.youtube.Object.Video;

public interface IClickItemVideo {
    void clickItemVideo(RecyclerViewAdapterAll.ViewHolder holder, Video video,int index);
}
