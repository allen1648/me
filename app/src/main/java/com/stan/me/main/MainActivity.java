package com.stan.me.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.stan.me.BaseActivity;
import com.stan.me.R;
import com.stan.me.movie.MovieFragment;

public class MainActivity extends BaseActivity implements MainContract.View {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuDrawable mMaterialMenuDrawable;
    private boolean mIsDrawerOpened;
    private MainPresenter mPresenter;
    private String mCurrentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawerLayout();
        initNavigationView();
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
        replaceFragment(MovieFragment.getInstance(), MovieFragment.TAG);
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("Material Dessign");
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitleMargin(0, 0, 0, 0);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mToolbar.setBackgroundColor(mPresenter.getStatusBarColor(MainActivity.this));
            }
        });
        mMaterialMenuDrawable = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        mToolbar.setNavigationIcon(mMaterialMenuDrawable);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsDrawerOpened) {
                    switchDrawer(false);
                } else {
                    switchDrawer(true);
                }
            }
        });
    }

    private void initDrawerLayout() {
        mDrawerLayout = findViewById(R.id.dl);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float slideOffset) {
                mPresenter.onDrawerSlide(slideOffset);
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
                mPresenter.onDrawerStateChanged(newState, mIsDrawerOpened);
            }
        });
    }

    private void initNavigationView() {
        final NavigationView nv = findViewById(R.id.nav_view);
        nv.addHeaderView(View.inflate(this, R.layout.navi_drawer_header_layout, null));
        nv.inflateMenu(R.menu.navi_drawer_menu);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                case R.id.nav_movie:
                    mPresenter.onMovieClick();
                    break;
                case R.id.nav_photopraphy:
                    mPresenter.onPhotoClick();
                    break;
                }
                return false;
            }
        });
    }

    @Override
    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        if (mCurrentFragmentTag != tag) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, tag).commit();
            mCurrentFragmentTag = tag;
        }
    }

    @Override
    public void setMenuTransformationOffset(float slideOffset) {
        mMaterialMenuDrawable.setTransformationOffset(
                MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                mIsDrawerOpened ? 2 - slideOffset : slideOffset
        );
    }

    @Override
    public void setMenuIconState(MaterialMenuDrawable.IconState iconState) {
        mMaterialMenuDrawable.setIconState(iconState);
    }

    @Override
    public void switchDrawer(boolean open) {
        if (open) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        } else {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
}
