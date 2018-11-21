package com.stan.me;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.stan.me.movie.MovieFragment;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable mMaterialMenuDrawable;
    private boolean mIsDrawerOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawerLayout();
        NavigationView nv = findViewById(R.id.nav_view);
        nv.addHeaderView(View.inflate(this, R.layout.navi_drawer_header_layout, null));
        nv.inflateMenu(R.menu.navi_drawer_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MovieFragment.getInstance()).commit();
    }

    private void initDrawerLayout() {
        mDrawerLayout = findViewById(R.id.dl);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float slideOffset) {
                mMaterialMenuDrawable.setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        mIsDrawerOpened ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                mIsDrawerOpened = true;

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                mIsDrawerOpened = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_IDLE) {
                    if (mIsDrawerOpened) {
                        mMaterialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.ARROW);
                    } else {
                        mMaterialMenuDrawable.setIconState(MaterialMenuDrawable.IconState.BURGER);
                    }
                }
            }
        });
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Material Dessign");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitleMargin(0, 0, 0, 0);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mToolbar.setBackgroundColor(getStatusBarColor());
            }
        });
        mMaterialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mToolbar.setNavigationIcon(mMaterialMenuDrawable);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsDrawerOpened) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    private int getStatusBarColor() {//onCreate方法还没有decorView
        View statusBar = findViewById(android.R.id.statusBarBackground);
        ColorDrawable cd = (ColorDrawable) statusBar.getBackground();
        return cd.getColor();
    }
}
