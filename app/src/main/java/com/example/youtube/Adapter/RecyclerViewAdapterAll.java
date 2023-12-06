package com.example.youtube.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtube.IClickItemSelected;
import com.example.youtube.IClickItemVideo;
import com.example.youtube.Object.ItemSelected;
import com.example.youtube.Object.Video;
import com.example.youtube.R;
import com.example.youtube.SwipeDismissTouchListener;

import java.util.ArrayList;

public class RecyclerViewAdapterAll extends RecyclerView.Adapter<RecyclerViewAdapterAll.ViewHolder> {

    final Context context;
    final ArrayList<Video> lstVideo;

    IClickItemVideo clickItemVideo;
    ArrayList<ItemSelected> lstItemSelected = new ArrayList<>();
    public RecyclerViewAdapterAll(Context context, ArrayList<Video> lstVideo,IClickItemVideo itemVideo) {
        this.context = context;
        this.lstVideo = lstVideo;
        this.clickItemVideo = itemVideo;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterAll.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_all,null);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterAll.ViewHolder holder, int position) {
        Video video = lstVideo.get(position);

        Glide.with(context).load(video.getThumbnail_video()).error(R.color.black).into(holder.img_thumbnail_video);
        Glide.with(context).load(video.getThumbnail_channel()).error(R.color.black).into(holder.img_thumbnail_channel);

        holder.tv_title_video.setText(video.getTitle_video());
        holder.tv_title_channel.setText(video.getTitle_channel());
        holder.tv_view.setText(video.getView() + " lượt xem");
        holder.tv_time.setText(video.getTime());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemVideo.clickItemVideo(holder,video,holder.getAdapterPosition());
            }
        });

        holder.more_info_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = View.inflate(context,R.layout.dialog_more_click,null);
                final Dialog dialog = new Dialog(context);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(dialogView);

                Window window = dialog.getWindow();

                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowManager = window.getAttributes();
                windowManager.gravity = Gravity.BOTTOM;
                windowManager.windowAnimations = R.style.showDialogMorePlay;
                window.setAttributes(windowManager);
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                LinearLayout layout_swipe_dismiss = dialog.findViewById(R.id.w_layout_swipe_dismiss);


//                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//                layoutParams.copyFrom(dialog.getWindow().getAttributes());
//                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                layoutParams.gravity = Gravity.BOTTOM;
//                dialog.getWindow().setAttributes(layoutParams);
                layout_swipe_dismiss.setOnTouchListener(new SwipeDismissTouchListener(dialog.getWindow().getDecorView(),
                        null, new SwipeDismissTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(Object token) {
                        return true;
                    }
                    @Override
                    public void onDismiss(View view, Object token) {
                        dialog.dismiss();
                    }
                }));


                lstItemSelected.clear();
                lstItemSelected.add(new ItemSelected(R.drawable.ic_edit,"Sửa thông tin Video"));
                lstItemSelected.add(new ItemSelected(R.drawable.ic_remove,"Xóa bài hát khỏi danh sách"));
                lstItemSelected.add(new ItemSelected(R.drawable.ic_setting,"Cài đặt"));

                RecyclerView rcv_more_click = dialog.findViewById(R.id.rcv_more_click);
                RecyclerViewAdapterUser adapterUser = new RecyclerViewAdapterUser(context, lstItemSelected, new IClickItemSelected() {
                    @Override
                    public void clickItemSelected(RecyclerViewAdapterUser.ViewHolder holder, ItemSelected itemSelected, int position) {
                        switch (position) {
                            case 0:
                                update_Info_Video(dialog);
                                break;
                            case 1: break;
                            case 2: break;
                            case 3: break;

                        }
                    }
                });
                rcv_more_click.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                rcv_more_click.setAdapter(adapterUser);

                dialog.show();
            }
        });
    }

    private void update_Info_Video(Dialog dialog) {

    }

    @Override
    public int getItemCount() {
        return lstVideo == null ? 0:lstVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_thumbnail_video,img_thumbnail_channel;
        ImageButton more_info_video;
        TextView tv_title_channel,tv_title_video,tv_view,tv_time;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            img_thumbnail_video = itemView.findViewById(R.id.img_thumbnail_video);
            img_thumbnail_channel = itemView.findViewById(R.id.img_thumbnail_channel);

            more_info_video = itemView.findViewById(R.id.more_info_video);

            tv_title_video = itemView.findViewById(R.id.tv_title_video);
            tv_title_channel = itemView.findViewById(R.id.tv_title_channel);
            tv_view = itemView.findViewById(R.id.tv_view);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
