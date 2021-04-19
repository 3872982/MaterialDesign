package com.learning.materialdesign.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.learning.materialdesign.R;
import com.learning.materialdesign.bean.Movie;
import com.learning.materialdesign.view.LoadingView;

import java.util.ArrayList;

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private ArrayList<Movie.SubjectsBean> mMovieList;
    private Context mContext;

    // 普通布局
    public final int TYPE_ITEM = 1;
    // 脚布局
    public final int TYPE_FOOTER = 2;
    // 当前加载状态，默认为加载完成
    private int loadState = 2;
    // 正在加载
    public final int LOADING = 1;
    // 加载完成
    public final int LOADING_COMPLETE = 2;
    // 加载到底
    public final int LOADING_END = 3;

    public LoadMoreAdapter(Context context,ArrayList<Movie.SubjectsBean> movieList) {
        mContext = context;
        mMovieList = movieList;
    }

    @Override
    public int getItemCount(){
        return mMovieList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mMovieList.size()){
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_ITEM){
            view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            view.setOnClickListener(this);
            return new RecyclerViewItemHolder(view);
        }else if(viewType == TYPE_FOOTER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_refresh_footer,parent,false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //正常布局的holder
        if (holder instanceof RecyclerViewItemHolder) {
            RecyclerViewItemHolder recyclerViewHolder = (RecyclerViewItemHolder) holder;

            recyclerViewHolder.tv_name.setText(mMovieList.get(position).getTitle());
            recyclerViewHolder.tv_star.setText(mMovieList.get(position).getYear());
            recyclerViewHolder.tv_year.setText(mMovieList.get(position).getRating().getAverage() + "");
            recyclerViewHolder.tv_avatars.setText(mMovieList.get(position).getCasts().get(0).getName());
            Glide.with(mContext).load(mMovieList.get(position).getImages().getMedium())
                    .centerCrop()
                    .crossFade(2000)
                    .into(recyclerViewHolder.iv_icon);
            holder.itemView.setTag(position);

        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (loadState) {
                case LOADING:
                    //正在加载中
                    footViewHolder.lvAtlvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.lvAtlvLoading.addBitmap(R.mipmap.v4);
                    footViewHolder.lvAtlvLoading.addBitmap(R.mipmap.v5);
                    footViewHolder.lvAtlvLoading.addBitmap(R.mipmap.v6);
                    footViewHolder.lvAtlvLoading.addBitmap(R.mipmap.v7);
                    footViewHolder.lvAtlvLoading.addBitmap(R.mipmap.v8);
                    footViewHolder.lvAtlvLoading.addBitmap(R.mipmap.v9);
                    footViewHolder.lvAtlvLoading.setShadowColor(Color.LTGRAY);
                    footViewHolder.lvAtlvLoading.setDuration(300);
                    footViewHolder.lvAtlvLoading.start();
                    footViewHolder.tvLoading.setVisibility(View.VISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    footViewHolder.llWarn.setVisibility(View.GONE);

                    break;
                case LOADING_COMPLETE:
                    //加载完成，还有数据
                    footViewHolder.lvAtlvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.tvLoading.setVisibility(View.INVISIBLE);
                    footViewHolder.llEnd.setVisibility(View.GONE);
                    footViewHolder.llWarn.setVisibility(View.VISIBLE);

                    break;
                case LOADING_END:
                    //没有数据
                    footViewHolder.lvAtlvLoading.setVisibility(View.GONE);
                    footViewHolder.tvLoading.setVisibility(View.GONE);
                    footViewHolder.llEnd.setVisibility(View.VISIBLE);
                    footViewHolder.llWarn.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    Log.w("Tag", gridManager.getSpanCount() + "--------");
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }


    /**
     * 正常条目的item的ViewHolder
     */
    private class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public ImageView iv_icon;
        public TextView tv_year;
        public TextView tv_avatars;
        public TextView tv_star;


        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_avatars = itemView.findViewById(R.id.tv_avatars);
            tv_year = itemView.findViewById(R.id.tv_year);
            tv_star = itemView.findViewById(R.id.tv_star);
            iv_icon = itemView.findViewById(R.id.iv_icon);

        }
    }

    /**
     * FootView的Holder
     */
    private class FootViewHolder extends RecyclerView.ViewHolder {

        /**
         * 进度条展示
         */
        LoadingView lvAtlvLoading;
        /**
         * 正在加载的TextView
         */
        TextView     tvLoading;
        /**
         * 服务器没有数据信息展示
         */
        LinearLayout llEnd;
        /**
         * 进行提示的布局信息
         */
        LinearLayout llWarn;

        FootViewHolder(View itemView) {
            super(itemView);
            lvAtlvLoading = itemView.findViewById(R.id.pb_loading);
            tvLoading = itemView.findViewById(R.id.tv_loading);
            llEnd = itemView.findViewById(R.id.ll_end);
            llWarn = itemView.findViewById(R.id.ll_warn);
        }
    }

    //===============================设置列表项点击监听事件
    private onItemClickListener mOnItemClickListener;

    public interface onItemClickListener{
        public void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
