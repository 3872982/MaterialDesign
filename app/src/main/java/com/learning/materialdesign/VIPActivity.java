package com.learning.materialdesign;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.learning.materialdesign.utils.SystemBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VIPActivity extends BaseActivity {

    @BindView(R.id.vip_image)
    ImageView mVipImage;
    @BindView(R.id.vip_layout)
    LinearLayout mVipLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //允许使用内容transitions
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        //分解动画
        getWindow().setEnterTransition(new Explode());
        //淡入动画
//        getWindow().setEnterTransition(new Fade());
        //滑动动画
//        getWindow().setEnterTransition(new Slide());
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mWebView.loadUrl("http://vip.bilibili.com/site/vip-faq-h5.html#yv1");
    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, mToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
