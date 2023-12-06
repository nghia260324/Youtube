package com.example.youtube.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtube.IClickItemSelected;
import com.example.youtube.Object.ItemSelected;
import com.example.youtube.R;

import java.util.ArrayList;

public class RecyclerViewAdapterUser extends RecyclerView.Adapter<RecyclerViewAdapterUser.ViewHolder> {

    final Context context;
    final ArrayList<ItemSelected> lstItem;
    IClickItemSelected clickItemSelected;

    public RecyclerViewAdapterUser(Context context, ArrayList<ItemSelected> lstItem,IClickItemSelected clickItemSelected) {
        this.context = context;
        this.lstItem = lstItem;
        this.clickItemSelected = clickItemSelected;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_selected_user,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterUser.ViewHolder holder, int position) {
        ItemSelected itemSelected = lstItem.get(position);

        holder.img_icon.setImageResource(itemSelected.getIcon());
        holder.tv_title.setText(itemSelected.getTitle());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemSelected.clickItemSelected(holder,itemSelected,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstItem == null ? 0:lstItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_icon;
        TextView tv_title;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img_icon = itemView.findViewById(R.id.img_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
