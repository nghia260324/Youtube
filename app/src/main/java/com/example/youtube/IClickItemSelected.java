package com.example.youtube;

import com.example.youtube.Adapter.RecyclerViewAdapterUser;
import com.example.youtube.Object.ItemSelected;

public interface IClickItemSelected {
    void clickItemSelected(RecyclerViewAdapterUser.ViewHolder holder, ItemSelected itemSelected, int position);
}
