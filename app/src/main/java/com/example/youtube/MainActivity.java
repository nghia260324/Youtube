package com.example.youtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {
    VideoView video_view;
    YouTubePlayerView youTubePlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        video_view = findViewById(R.id.video_view);

//        Uri uri = Uri.parse("https://www.youtube.com/watch?v=6-LmZLjS5WI");
//        video_view.setVideoURI(uri);
//        video_view.requestFocus();
//        video_view.start();
//        String frameVideo = "<html><body><br><iframe width=\"100%\" height=\"270px\" src=\"https://www.youtube.com/embed/6-LmZLjS5WI\" title=\"LÀ ANH - Cover Nhạc Ngoại Lời Việt by PHẠM LỊCH ( It’s You -Mộng Nhiên)\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe></body></html>";
//
//        WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
//        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        WebSettings webSettings = displayYoutubeVideo.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");

        youTubePlayer = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver((LifecycleObserver) youTubePlayer);

//        youTubePlayer.addListener(new YouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                String videoId = "S0Q4gqBUs7c";
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//
//            @Override
//            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState playerState) {
//
//            }
//
//            @Override
//            public void onPlaybackQualityChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlaybackQuality playbackQuality) {
//
//            }
//
//            @Override
//            public void onPlaybackRateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlaybackRate playbackRate) {
//
//            }
//
//            @Override
//            public void onError(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerError playerError) {
//
//            }
//
//            @Override
//            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float v) {
//
//            }
//
//            @Override
//            public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float v) {
//
//            }
//
//            @Override
//            public void onVideoLoadedFraction(@NonNull YouTubePlayer youTubePlayer, float v) {
//
//            }
//
//            @Override
//            public void onVideoId(@NonNull YouTubePlayer youTubePlayer, @NonNull String s) {
//
//            }
//
//            @Override
//            public void onApiChange(@NonNull YouTubePlayer youTubePlayer) {
//
//            }
//        });

        youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "6-LmZLjS5WI";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}