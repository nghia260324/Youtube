package com.example.youtube;

import com.example.youtube.Adapter.RecyclerViewAdapterCategory;
import com.example.youtube.Object.Category;

public interface IClickItemCategory {
    void clickItemCategory(RecyclerViewAdapterCategory.ViewHolder holder, Category category, int position);
}
