package com.learning.materialdesign.listener;

import com.google.android.material.appbar.AppBarLayout;

public abstract class AppBarStateChangedListener implements AppBarLayout.OnOffsetChangedListener {
    protected enum State{
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mState = State.IDLE;

    public abstract void onStateChangedListener(AppBarLayout appBarLayout,State state);

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if(verticalOffset <= 0 && mState != State.EXPANDED){
            onStateChangedListener(appBarLayout,State.EXPANDED);
            mState = State.EXPANDED;
        }else if(Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange() && mState != State.COLLAPSED){
            onStateChangedListener(appBarLayout,State.COLLAPSED);
            mState = State.COLLAPSED;
        }
    }
}
