package com.learning.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //浮动菜单
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "snack action ", 1000)
                        .setAction("Toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, " to do ", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        //将Toolbar与Drawerlayout侧滑菜单关联
        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //侧滑菜单事件监听
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    //处理NavigationView侧滑菜单的点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //无论选哪个，先关了侧滑栏
        mDrawer.closeDrawer(GravityCompat.START);

        int id = item.getItemId();

        switch (id){
            case R.id.nav_camera:
                Toast.makeText(this, "nav_camera", Toast.LENGTH_SHORT).show();
                goActivity(ToolbarActivity.class);
                break;
            case R.id.nav_gallery:
                Toast.makeText(this, "nav_gallery", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_slideshow:
                Toast.makeText(this, "nav_floatTab", Toast.LENGTH_SHORT).show();
                goActivity(FloatTabActivity.class);
                break;
            case R.id.nav_vip:
                Toast.makeText(this, "nav_vip", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_bottom_navigation:
                Toast.makeText(this, "nav_bottom_navigation", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //按了返回键
    @Override
    public void onBackPressed() {
        //如果侧滑菜单打开着，就关闭侧滑菜单
        if(mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    //为toolbar添加选项action
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Toolbar 响应Action
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "action_settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}