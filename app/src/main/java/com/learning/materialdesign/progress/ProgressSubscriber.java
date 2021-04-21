package com.learning.materialdesign.progress;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;


/**
 * Created by Administrator on 2017/6/5.
 */

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressHandler.ProgressDialogCancelListener {

    private Context mContext;
    private OnNextListener<T> mOnNextListener;
    private ProgressHandler mProgressHandler;

    public ProgressSubscriber(Context context, Boolean cancelable, OnNextListener<T> onNextListener) {
        mContext = context;
        mOnNextListener = onNextListener;

        //在主线程中
        mProgressHandler = new ProgressHandler(mContext,cancelable,this);
    }

    private void showProgressDialog() {
        if(mProgressHandler != null){
            mProgressHandler.obtainMessage(ProgressHandler.SHOW_DIALOG).sendToTarget();
        }

    }

    private void dismissProgressDialog() {
        if(mProgressHandler != null){
            mProgressHandler.obtainMessage(ProgressHandler.DISMISS_DIALOG).sendToTarget();
            mProgressHandler = null;
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(mContext, "数据加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof SocketTimeoutException){
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        }else if(e instanceof ConnectException){
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if(mOnNextListener != null){
            mOnNextListener.onNext(t);
        }
    }

    /**
     * 取消dialog对话框的同时，取消对Observable的订阅
     */
    @Override
    public void onCancel() {
        if(this.isUnsubscribed()){
            this.unsubscribe();
        }
    }

    public interface OnNextListener<T>{
        void onNext(T t);
    }
}
