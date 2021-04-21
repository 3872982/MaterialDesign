package com.learning.materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.learning.materialdesign.listener.AppBarStateChangedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_movie_icon)
    ImageView mIvMovieIcon;
    @BindView(R.id.tb_amd_toolbar)
    Toolbar mTbAmdToolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.dl_at_draw_layout)
    DrawerLayout mDlAtDrawLayout;
    private Unbinder mBind;
    private String mUrl;
    private String mName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mBind = ButterKnife.bind(this);

        mUrl = getIntent().getStringExtra("URL");
        mName = getIntent().getStringExtra("NAME");

        initView();
    }

    private void initView() {
        //设置toolbar，在有CollapsingToolbar时，需要将title设置到CollapsingToolbar上，设置在toolbar上面不显示
        setSupportActionBar(mTbAmdToolbar);

        //设置collapsingToolbar
        mCollapsingToolbarLayout.setTitle(mName);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);

        mAppBar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChangedListener(AppBarLayout appBarLayout, State state) {
                if(state == State.COLLAPSED){
                    mTbAmdToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else{
                    mTbAmdToolbar.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        Glide.with(this).load(mUrl).centerCrop().crossFade(200).into(mIvMovieIcon);

        mTvContent.setText("1.自贸港政策制度框架建立\n" +
                "\n" +
                "“2019年博鳌亚洲论坛年会期间，我在博鳌免税店买了不少东西，有化妆品、箱包等，挺划算的，今年打算继续逛逛。”论坛参会代表刘女士表示。\n" +
                "\n" +
                "2019年1月开业的博鳌免税店在当年论坛期间销售火爆，吸引了大批与会代表前往扫货。据统计仅2019年3月26日至28日，购物人数就超过3000人，三天销售总收入近800万元。\n" +
                "\n" +
                "离岛免税是海南自贸港核心政策之一，自2020年7月海南离岛旅客每年每人免税购物额度提高至10万元以来，海口、三亚的免税店内常常人潮如织，频现排队长龙，日均销售额超过1.2亿元，同比增长两倍多。\n" +
                "\n" +
                "前不久，海南一龄医疗产业发展有限公司从法国进口的低温理疗箱，成为海南自贸港自用生产设备零关税政策项下的首批货物，货值358万元、减免税款约83万元。\n" +
                "\n" +
                "这些都只是海南自贸港政策带来早期收获的一个个缩影。三年来，尤其是《海南自由贸易港建设总体方案》发布实施以来，我国研究出台了一系列支持政策，推动海南自贸港建设顺利开局。初步统计，目前已发布政策文件110多份，自由贸易港政策制度框架初步建立。包括更加自由便利的贸易投资政策、更加安全便捷的金融支持政策、更加高效精准的税收优惠政策、更加便利开放的运输服务政策、更加有力有效的要素支撑政策、更加高效完善的实施保障制度。\n" +
                "\n" +
                "2.高水平开放图景初现\n" +
                "\n" +
                "博鳌乐城国际医疗旅游先行区，距离博鳌亚洲论坛永久会址10多公里，4月13日一场国际创新药械展在这里开幕。来自16个国家36个地区的80家全球参展厂商带来810种药械产品参展，其中441种未在国内上市、394种首次在国内亮相。\n" +
                "\n" +
                "波士顿科学是第二次参加在乐城先行区长期举行的国际创新药械展。与第一次相比，这次该公司带来的产品更多，也更加先进。“这次带来了公司旗下63款亮点产品，几乎每一条产品线的代表产品都来了。”波士顿科学展馆工作人员说。\n" +
                "\n" +
                "作为海南自贸港重点园区，博鳌乐城国际医疗旅游先行区正在推动国际先进医疗资源与国内健康需求有效衔接，目前世界排名前30强的药械企业近八成与园区建立直接合作关系，在区内使用的未在国内上市创新药械已达135种，其中不乏全球首例应用的创新产品。\n" +
                "\n" +
                "除了医疗领域，教育领域的开放也同样精彩：海南已签约引进中国大陆首个境外高水平大学独立办学项目德国比勒菲尔德应用科技大学；酒店管理专业世界知名的瑞士洛桑酒店管理学院也将在海南独立办学，计划今年秋季开学。\n" +
                "\n" +
                "作为中外合作办学的集中展示区，海南陵水黎安国际教育创新试验区建设工地正在加紧建设，工人们加班加点，试验区管理局计划今年9月交付公共教学楼（一期）、体育场等工程，为首批招生做准备。");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}
