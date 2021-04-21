package com.learning.materialdesign;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomNavigationViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.bnv_bottom_nav)
    BottomNavigationView mBnvBottomNav;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        mBind = ButterKnife.bind(this);
        mBnvBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        mTvText.setText("Home按钮被点击啦！");
                        return true;
                    case R.id.navigation_dashboard:
                        mTvText.setText("dashboard按钮被点击啦！");
                        return true;
                    case R.id.navigation_notifications:
                        mTvText.setText("notifications按钮被点击啦！");
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
