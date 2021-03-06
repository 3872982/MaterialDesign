package com.learning.materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.learning.materialdesign.adapter.FragmentsAdapter;
import com.learning.materialdesign.fragment.MovieFragment;
import com.learning.materialdesign.fragment.MoviesFragment;
import com.learning.materialdesign.net.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FloatTabActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tb_atf_toolbar)
    Toolbar mTbAtfToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.tb_toolbar)
    TabLayout mTbToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.main_vp_container)
    ViewPager mMainVpContainer;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    private Unbinder mBind;
    private Window mWindow;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_tab);
        mBind = ButterKnife.bind(this);
        mWindow = getWindow();

        //======================??????banner==========================
        initBanner();

        //======================??????toolbar==========================
        initToolbar();

        //======================??????collapsingToolbar=================
        initCollapsingToolbarLayout();

        //======================??????TabLayout+ViewPager===============

        //??????setupWithViewPager???????????????removeAllTabs();????????????adapter??????getPageTitle?????????????????????tabs
        //setupWithViewPager ==> setPagerAdapter(adapter, autoRefresh) ==> removeAllTabs()
        //???????????????????????????????????????
        //mTbToolbar.setupWithViewPager(mMainVpContainer);

        //??????????????????ViewPager???TabLayout????????????????????????????????????????????????
        mMainVpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTbToolbar));
        mTbToolbar.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mMainVpContainer));
        //????????????????????????????????????????????????



        initFragments();
//
        mMainVpContainer.setAdapter(new FragmentsAdapter(getSupportFragmentManager(), mFragmentList));
        mMainVpContainer.setOffscreenPageLimit(5);
    }

    private void initFragments() {
        mFragmentList.add(new MovieFragment());
        mFragmentList.add(new MoviesFragment());
        mFragmentList.add(new MoviesFragment());
        mFragmentList.add(new MoviesFragment());
        mFragmentList.add(new MoviesFragment());
    }

    /**
     * ????????????Collapsing??????????????????????????????????????????
     * ??????CollapsingToolbarLayout??????toolbar?????????????????????CollapsingToolbarLayout????????????????????????
     */
    private void initCollapsingToolbarLayout() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mBanner.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("MaterialDesign");
                    //??????????????????CollapsingToolbarLayout???????????????????????????->???????????????????????????
                    mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

                    mWindow.setStatusBarColor(getResources().getColor(R.color.fuck));
                } else {
                    mCollapsingToolbarLayout.setTitle("");
                    mWindow.setStatusBarColor(Color.TRANSPARENT);
                }
            }
        });
    }

    private void initBanner() {
        //?????????????????????
        mBanner.setImageLoader(new GlideImageLoader());
        //??????????????????
        Integer[] images = {R.mipmap.v0, R.mipmap.v1, R.mipmap.v2, R.mipmap.v3};
        //String[] titles = {"sdssd","sdsd","kwk","sddsg"};
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setImages(Arrays.asList(images));
        //mBanner.setBannerTitles(Arrays.asList(titles));
        // ?????????????????????
        //mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner?????????????????????????????????????????????
        mBanner.start();
    }

    private void initToolbar() {
        setSupportActionBar(mTbAtfToolbar);
        mTbAtfToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
