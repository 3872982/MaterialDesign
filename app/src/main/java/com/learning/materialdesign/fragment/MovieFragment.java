package com.learning.materialdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.learning.materialdesign.DetailActivity;
import com.learning.materialdesign.R;
import com.learning.materialdesign.adapter.LoadMoreAdapter;
import com.learning.materialdesign.adapter.MovieAdapter;
import com.learning.materialdesign.bean.Subjects;
import com.learning.materialdesign.net.HttpMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;

public class MovieFragment extends Fragment {

    @BindView(R.id.rv_fragment)
    RecyclerView mRvFragment;
    @BindView(R.id.srl_fragment)
    SwipeRefreshLayout mSrlFragment;
    private Unbinder mBind;
    private int mCurrentPage = 1;
    private boolean isFirstIn = true;
    private List<Subjects.SubjectBean> mSubjectList = new ArrayList<>();
    private MovieAdapter mMovieAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview, null,false);
        mBind = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        //System.out.println("===========initView");
        mRvFragment.setLayoutManager(new LinearLayoutManager(getContext()));

        mSrlFragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSubjectList.clear();
                mCurrentPage = 1;
                initData();
            }
        });
    }

    private void initData() {
        //System.out.println("===========initData");
        mSrlFragment.setRefreshing(true);

        Subscriber<Subjects> subscriber = new Subscriber<Subjects>() {
            @Override
            public void onCompleted() {
                //System.out.println("===========onCompleted");
                mSrlFragment.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                //System.out.println("===========onError"+e.getMessage());
                mSrlFragment.setRefreshing(false);
            }

            @Override
            public void onNext(Subjects subjects) {
                //第一次进
                if(mMovieAdapter == null){
                    mSubjectList.addAll(subjects.subject);
                    //System.out.println("==========="+mSubjectList.size());
                    mMovieAdapter = new MovieAdapter(getContext(), mSubjectList);
                    mRvFragment.setAdapter(mMovieAdapter);
                    mMovieAdapter.setOnItemClickListener(new LoadMoreAdapter.onItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                    new Pair<View, String>(view.findViewById(R.id.iv_icon), "basic"));
                            Intent intent = new Intent(getActivity(), DetailActivity.class);
                            intent.putExtra("URL", mSubjectList.get(position).img);
                            intent.putExtra("NAME", mSubjectList.get(position).title);
                            startActivity(intent,activityOptionsCompat.toBundle());
                        }
                    });
                }else {//说明从刷新进来的，这里偷懒没有实现加载更多的逻辑
                    mSubjectList.addAll(subjects.subject);
                    mMovieAdapter.notifyDataSetChanged();
                }
            }
        };
        HttpMethod.getInstance().getTopMovie(subscriber,mCurrentPage);
    }

    @Override
    public void onDestroy() {
        mBind.unbind();
        super.onDestroy();
    }
}
