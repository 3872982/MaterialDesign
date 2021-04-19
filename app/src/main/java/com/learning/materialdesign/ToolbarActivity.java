package com.learning.materialdesign;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.learning.materialdesign.adapter.EndlessRecyclerOnScrollListener;
import com.learning.materialdesign.adapter.LoadMoreAdapter;
import com.learning.materialdesign.bean.Movie;

import java.util.ArrayList;

public class ToolbarActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RecyclerView mRv_at_list;
    private Toolbar mTb_at_toolbar;
    private SwipeRefreshLayout mSrl_refresh;
    private ArrayList<Movie.SubjectsBean> mMovieList = new ArrayList<>();

    //模拟网页数据翻页获取
    private int mStart = 0;
    private int mEnd = 10;
    private LoadMoreAdapter mLoadMoreAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        mDrawerLayout = findViewById(R.id.dl_at_draw_layout);
        mRv_at_list = findViewById(R.id.rv_at_list);
        mTb_at_toolbar = findViewById(R.id.tb_at_toolbar);
        mSrl_refresh = findViewById(R.id.srl_refresh);

        //=================设置toolbar====================================
        setSupportActionBar(mTb_at_toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置Toolbar home键可点击
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置Toolbar home键图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_am);
        }

        //关联toolbar与Drawerlayout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mTb_at_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //浮动按钮
        FloatingActionButton fabButton = findViewById(R.id.fab_at_action);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出提示
                Snackbar.make(v, "snack action ", 1000)
                        //Snackbar点击响应
                        .setAction("Toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ToolbarActivity.this, " to do ", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRv_at_list.setLayoutManager(linearLayoutManager);


        //下拉刷新
        mSrl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMovieList.clear();
                mEnd = 10;
                loadDataFromNet(mStart,mEnd,true);
            }
        });
        //设置正在更新数据
        mSrl_refresh.setRefreshing(true);
        loadDataFromNet(mStart,mEnd,false);

        mRv_at_list.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMoreData() {
                mLoadMoreAdapter.setLoadState(mLoadMoreAdapter.LOADING);

                //模拟耗时加载数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mMovieList.size() <50){
                            loadDataFromNet(mStart,mEnd,false);
                            mEnd += 10;
                        }else{
                            mLoadMoreAdapter.setLoadState(mLoadMoreAdapter.LOADING_END);
                        }
                    }
                },2000);
            }
        });
    }

    private void loadDataFromNet(int start,int end,boolean isRefresh) {
        //这里其实应该根据参数加载对应数据，由于是模拟就不判断了
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());
        mMovieList.add(new Movie.SubjectsBean());

        //第一次加载数据
        if(mLoadMoreAdapter == null){
            mLoadMoreAdapter = new LoadMoreAdapter(this,mMovieList);
            mRv_at_list.setAdapter(mLoadMoreAdapter);
        }else{
            mLoadMoreAdapter.notifyDataSetChanged();
        }

        mSrl_refresh.setRefreshing(false);
        mLoadMoreAdapter.setLoadState(mLoadMoreAdapter.LOADING_COMPLETE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        if (id == R.id.add) {
            Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.delete){
            Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.tb_setting){
            Toast.makeText(this, "tb_setting", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
