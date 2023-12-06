package com.example.youtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.youtube.Adapter.RecyclerViewAdapterCategory;
import com.example.youtube.Adapter.RecyclerViewAdapterUser;
import com.example.youtube.Fragment.AllFragment;
import com.example.youtube.Fragment.LiveFragment;
import com.example.youtube.Fragment.MusicFragment;
import com.example.youtube.Object.Category;
import com.example.youtube.Object.ItemSelected;
import com.example.youtube.Object.Video;
import com.example.youtube.SQL.DataBase;
import com.example.youtube.SQL.EventSQL;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ArrayList<Category> lstCategory;
    ArrayList<ItemSelected> lstItem;
    RecyclerView selected_category;
    FrameLayout frameLayout_home;
    ImageView img_thumbnail_user;
    Button btn_next_category;
    EventSQL eventSQL = new EventSQL(this);

    DataBase dataBase = new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lstCategory = new ArrayList<>();
        lstItem = new ArrayList<>();

        findID();

        addListCategory();
        setAdapterCategory();

        imgThumbnailUser();

        replaceFragment(new AllFragment());

    }

    private void imgThumbnailUser() {
        img_thumbnail_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = View.inflate(HomeActivity.this,R.layout.dialog_click_thumbnail_user,null);

                final Dialog dialog = new Dialog(HomeActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(dialogView);

                dialog.getWindow().getAttributes().windowAnimations = R.style.showDialogUser;

                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);

                lstItem.clear();
                lstItem.add(new ItemSelected(R.drawable.ic_user,"Kênh của bạn"));
                lstItem.add(new ItemSelected(R.drawable.ic_incognito,"Bật chế độ ẩn danh"));
                lstItem.add(new ItemSelected(R.drawable.ic_add_user,"Thêm tài khoản"));
                lstItem.add(new ItemSelected(R.drawable.ic_setting,"Cài đặt"));

                RecyclerView list_item_user = dialog.findViewById(R.id.list_item_user);
                ImageButton btn_close_user = dialog.findViewById(R.id.btn_close_user);

                btn_close_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                RecyclerViewAdapterUser adapterUser = new RecyclerViewAdapterUser(HomeActivity.this, lstItem, new IClickItemSelected() {
                    @Override
                    public void clickItemSelected(RecyclerViewAdapterUser.ViewHolder holder, ItemSelected itemSelected, int position) {


                    }
                });

                list_item_user.setLayoutManager(new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false));
                list_item_user.setAdapter(adapterUser);

                dialog.show();
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_home,fragment);
        fragmentTransaction.addToBackStack(AllFragment.class.getName());
        fragmentTransaction.commit();
    }
    private void setAdapterCategory() {
        RecyclerViewAdapterCategory adapterCategory = new RecyclerViewAdapterCategory(this, lstCategory, new IClickItemCategory() {
            @Override
            public void clickItemCategory(RecyclerViewAdapterCategory.ViewHolder holder, Category category, int position) {
                switch (position) {
                    case 0: replaceFragment(new AllFragment()); break;
                    case 1: replaceFragment(new LiveFragment()); break;
                    case 2: replaceFragment(new MusicFragment()); break;
                    case 3: replaceFragment(new AnimeFragment());break;
                    case 4: break;
                    case 5: break;
                    case 6: break;
                    case 7: break;
                }
            }
        });
        selected_category.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        selected_category.setAdapter(adapterCategory);

        selected_category.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx >0) {
                    if (btn_next_category.isShown()) {
                        btn_next_category.setVisibility(View.GONE);
                        Animation animation = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.alpha_out);
                        btn_next_category.startAnimation(animation);
                    }
                }
                else if (dx <0) {
                    if (!btn_next_category.isShown()) {
                        btn_next_category.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(HomeActivity.this,R.anim.alpha_in);
                        btn_next_category.startAnimation(animation);
                    }
                }
            }
        });
    }
    FirebaseAuth firebaseAuth;
    private void login() {
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void addListCategory() {
        lstCategory.add(new Category("All"));
        lstCategory.add(new Category("Live"));
        lstCategory.add(new Category("Music"));
        lstCategory.add(new Category("Anime"));
        lstCategory.add(new Category("Gaming"));
        lstCategory.add(new Category("News"));
        lstCategory.add(new Category("Esports"));
        lstCategory.add(new Category("Comedy"));
    }
    private void findID() {
        selected_category = findViewById(R.id.selected_category);
        frameLayout_home = findViewById(R.id.frameLayout_home);

        img_thumbnail_user = findViewById(R.id.img_thumbnail_user);

        btn_next_category = findViewById(R.id.btn_next_category);
    }
}