package com.example.youtube;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.youtube.Adapter.RecyclerViewAdapterAll;
import com.example.youtube.Object.Video;
import com.example.youtube.SQL.EventSQL;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimeFragment newInstance(String param1, String param2) {
        AnimeFragment fragment = new AnimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView rcv_video_anime;
    ArrayList<Video> lstVideo_Anime;
    EventSQL eventSQL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anime, container, false);

        eventSQL = new EventSQL(getContext());
        lstVideo_Anime = new ArrayList<>();

        lstVideo_Anime = eventSQL.getAllVideo("VIDEO_ANIME");

        rcv_video_anime = view.findViewById(R.id.rcv_video_anime);

        RecyclerViewAdapterAll adapterAll = new RecyclerViewAdapterAll(getContext(), lstVideo_Anime, new IClickItemVideo() {
            @Override
            public void clickItemVideo(RecyclerViewAdapterAll.ViewHolder holder, Video video, int index) {
                final View dialogView = View.inflate(getContext(),R.layout.dialog_play_video,null);

                final Dialog dialog = new Dialog(getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(dialogView);

                dialog.getWindow().getAttributes().windowAnimations = R.style.showDialogUser;

                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);

//                WebView webView = dialogView.findViewById(R.id.vv_playVideo);
                YouTubePlayerView youtube_playerView = dialogView.findViewById(R.id.youtube_playerView);
//                getLifecycle().addObserver(youtube_playerView);

//                youtube_playerView = new YouTubePlayerView(getContext());
                youtube_playerView.initialize(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        super.onReady(youTubePlayer);

                        String videoId = video.getPath_video();
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });

                TextView tv_title_video = dialog.findViewById(R.id.tv_title_video);
                TextView tv_view_video = dialog.findViewById(R.id.tv_view_video);
                TextView tv_title_channel = dialog.findViewById(R.id.tv_title_channel);
                ImageView img_thumbnail_channel = dialog.findViewById(R.id.img_thumbnail_channel);
                TextView tv_subscribe = dialog.findViewById(R.id.tv_subscribe);
                TextView tv_like = dialog.findViewById(R.id.tv_like);
                TextView tv_comment = dialog.findViewById(R.id.tv_comment);
                RecyclerView rcv_randomVideo = dialog.findViewById(R.id.rcv_randomVideo);
                HorizontalScrollView scrollniew_h = dialog.findViewById(R.id.scrollniew_h);
                Button btn_next_item = dialog.findViewById(R.id.btn_next_item);
                ScrollView scroll_playvideo = dialog.findViewById(R.id.scroll_playvideo);

                scrollniew_h.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int scrollX = scrollniew_h.getScrollX();

                        if (scrollX >0) {
                            if (btn_next_item.isShown()) {
                                btn_next_item.setVisibility(View.GONE);
                                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.alpha_out);
                                btn_next_item.startAnimation(animation);
                            }
                        }
                        else if (scrollX <0) {
                            if (!btn_next_item.isShown()) {
                                btn_next_item.setVisibility(View.VISIBLE);
                                Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.alpha_in);
                                btn_next_item.startAnimation(animation);
                            }
                        }
                        return false;
                    }
                });

                loadVideo(youtube_playerView,  video,  img_thumbnail_channel,
                        tv_title_channel, tv_title_video,  tv_view_video,
                        tv_subscribe,  tv_like,  tv_comment,  rcv_randomVideo);

                dialog.show();
            }
        });
        rcv_video_anime.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rcv_video_anime.setAdapter(adapterAll);


        return view;
    }
    private void loadVideo(YouTubePlayerView youtube_playerView,Video video,ImageView img_thumbnail_channel,
                           TextView tv_title_channel,TextView tv_title_video, TextView tv_view_video,
                           TextView tv_subscribe, TextView tv_like, TextView tv_comment,RecyclerView rcv_randomVideo) {

        Glide.with(getContext()).load(video.getThumbnail_channel()).error(R.color.black).into(img_thumbnail_channel);

        tv_title_channel.setText(video.getTitle_channel());
        tv_title_video.setText(video.getTitle_video());
        tv_view_video.setText(video.getView() + " lượt xem" + "     " + video.getTime());
        tv_subscribe.setText(video.getSub());
        tv_like.setText(video.getLike());
        tv_comment.setText(video.getComment());
    }
}