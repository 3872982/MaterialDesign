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

        //======================设置banner==========================
        initBanner();

        //======================设置toolbar==========================
        initToolbar();

        //======================设置collapsingToolbar=================
        initCollapsingToolbarLayout();

        //======================设置TabLayout+ViewPager===============

        //使用setupWithViewPager内部会调用removeAllTabs();然后根据adapter中的getPageTitle去重新生成新的tabs
        //setupWithViewPager ==> setPagerAdapter(adapter, autoRefresh) ==> removeAllTabs()
        //这就会造成图标不显示的问题
        //mTbToolbar.setupWithViewPager(mMainVpContainer);

        //自己手动实现ViewPager与TabLayout的双向绑定，这样图标就不会变换了
        mMainVpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTbToolbar));
        mTbToolbar.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mMainVpContainer));
        //相互绑定，这样可以防止图标被替换



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
     * 滑动小于Collapsing一半时，显示标题，否则不显示
     * 使用CollapsingToolbarLayout时，toolbar的标题要设置到CollapsingToolbarLayout上才可以正常显示
     */
    private void initCollapsingToolbarLayout() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mBanner.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("MaterialDesign");
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
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
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        Integer[] images = {R.mipmap.v0, R.mipmap.v1, R.mipmap.v2, R.mipmap.v3};
        //String[] titles = {"sdssd","sdsd","kwk","sddsg"};
        mBanner.setBannerAnimation(Transformer.Default);
        mBanner.setImages(Arrays.asList(images));
        //mBanner.setBannerTitles(Arrays.asList(titles));
        // 显示圆形指示器
        //mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
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
