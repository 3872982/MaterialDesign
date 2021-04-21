package com.learning.materialdesign.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Richen.Xu on 2017/6/5.
 */

/**
 * 用来处理ProgressDialog的显示与取消
 */
public class ProgressHandler extends Handler {

    public static final int SHOW_DIALOG = 100;
    public static final int DISMISS_DIALOG = 101;

    private ProgressDialog mProgressDialog;

    private Context mContext;
    private boolean mCancelable;
    private ProgressDialogCancelListener mProgressDialogCancelListener;


    public ProgressHandler(Context context, boolean cancelable, ProgressDialogCancelListener progressDialogCancelListener){
        this.mContext = context;
        this.mCancelable = cancelable;
        this.mProgressDialogCancelListener = progressDialogCancelListener;
    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case SHOW_DIALOG:
                showDialog();
                break;
            case DISMISS_DIALOG:
                dismissDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 显示ProgressDialog
     */
    private void showDialog() {
        if(mProgressDialog == null){
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("数据加载中....");
        }

        /**设置是否能够取消，以及取消所触发的事件*/
        mProgressDialog.setCancelable(mCancelable);

        if(mCancelable){
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mProgressDialogCancelListener.onCancel();
                }
            });
        }

        if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    /**
     * 取消ProgressDialog
     */
    private void dismissDialog() {

        if(mProgressDialog != null){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    public interface ProgressDialogCancelListener{
        void onCancel();
    }
}
