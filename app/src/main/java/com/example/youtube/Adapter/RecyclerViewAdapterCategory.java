package com.example.youtube.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube.Object.Category;
import com.example.youtube.IClickItemCategory;
import com.example.youtube.R;

import java.util.ArrayList;

public class RecyclerViewAdapterCategory extends RecyclerView.Adapter<RecyclerViewAdapterCategory.ViewHolder> {

    final Context context;

    final ArrayList<Category> lstCategory;

    IClickItemCategory clickItemCategory;
    int selectedPosition = 0;
    int lastSelectedPosition = -1;

    public RecyclerViewAdapterCategory(Context context, ArrayList<Category> lstCategory,IClickItemCategory itemCategory) {
        this.context = context;
        this.lstCategory = lstCategory;
        clickItemCategory = itemCategory;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_category,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterCategory.ViewHolder holder, int position) {
        int a = position;
        Category category = lstCategory.get(position);

        holder.title.setText(category.getTitle());
        holder.bgr_title.setText(category.getTitle());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemCategory.clickItemCategory(holder,category,a);

                lastSelectedPosition = selectedPosition;
                selectedPosition = a;

                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });
        if (selectedPosition == holder.getAdapterPosition()) {
            Drawable category_selected = context.getDrawable(R.drawable.bgr_category_selected);
            Drawable bgr_category = context.getDrawable(R.drawable.bgr_category_selected_click);
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.alpha_selected_category);
            holder.title.setBackground(category_selected);
            holder.bgr_title.setBackground(bgr_category);
            holder.bgr_title.startAnimation(animation);

        } else {
            Drawable category_selected = context.getDrawable(R.drawable.bgr_category);
            Drawable bgr_category = context.getDrawable(R.drawable.bgr_tran);
            holder.title.setBackground(category_selected);
            holder.bgr_title.setBackground(bgr_category);
        }
    }

    @Override
    public int getItemCount() {
        return lstCategory == null ? 0:lstCategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,bgr_title;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.title_category);
            bgr_title = itemView.findViewById(R.id.tv_bgr_selected);
        }
    }
}
