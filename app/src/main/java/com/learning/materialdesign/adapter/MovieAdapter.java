package com.learning.materialdesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.learning.materialdesign.R;
import com.learning.materialdesign.bean.Subjects;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<Subjects.SubjectBean> mSubjectList;
    private Context mContext;

    public MovieAdapter(Context context,List<Subjects.SubjectBean> subjectList) {
        mContext = context;
        mSubjectList = subjectList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        view.setOnClickListener(this);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder)holder;
        itemHolder.bindView(mContext,mSubjectList.get(position));
        itemHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tv_name;
        public ImageView iv_icon;
        public TextView tv_quote;
        public TextView tv_director;
        public TextView tv_star;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_quote = itemView.findViewById(R.id.tv_quote);
            tv_director = itemView.findViewById(R.id.tv_director);
            tv_star = itemView.findViewById(R.id.tv_star);
        }

        public void bindView(Context context, Subjects.SubjectBean subject){
            tv_name.setText(subject.title);
            tv_quote.setText(subject.quote);
            tv_director.setText(subject.director);
            tv_star.setText(subject.score);
            Glide.with(mContext).load(subject.img)
                    .centerCrop()
                    .crossFade(2000)
                    .into(iv_icon);
        }
    }

    //===============================设置列表项点击监听事件
    private LoadMoreAdapter.onItemClickListener mOnItemClickListener;

    public interface onItemClickListener{
        public void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(LoadMoreAdapter.onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
