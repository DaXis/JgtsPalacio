package com.jgtspalacio.custom;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class LoadToast {

    private String mText = "";
    private LoadToastView mView;
    private int mTranslationY = 0;
    private boolean mShowCalled = false;
    private boolean mInflated = false;

    public LoadToast(Context context){
        mView = new LoadToastView(context);
        final ViewGroup vg = (ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        vg.addView(mView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHelper.setAlpha(mView, 0);
        vg.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewHelper.setTranslationX(mView, (vg.getWidth() - mView.getWidth()) / 2);
                ViewHelper.setTranslationY(mView, -mView.getHeight() + mTranslationY);
                mInflated = true;
                if(mShowCalled) show();
            }
        },1);
    }

    public LoadToast setTranslationY(int pixels){
        mTranslationY = pixels;
        return this;
    }

    public LoadToast setText(String message){
        mText = message;
        mView.setText(mText);
        return this;
    }

    public LoadToast setTextColor(int color){
        mView.setTextColor(color);
        return this;
    }

    public LoadToast setBackgroundColor(int color){
        mView.setBackgroundColor(color);
        return this;
    }

    public LoadToast setProgressColor(int color){
        mView.setProgressColor(color);
        return this;
    }

    public LoadToast show(){
        if(!mInflated){
            mShowCalled = true;
            return this;
        }
        mView.show();
        ViewHelper.setAlpha(mView, 0f);
        ViewHelper.setTranslationY(mView, -mView.getHeight() + mTranslationY);
        //mView.setVisibility(View.VISIBLE);
        ViewPropertyAnimator.animate(mView).alpha(1f).translationY(25 + mTranslationY)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(300).setStartDelay(0).start();
        return this;
    }

    public void success(){
        mView.success();
        slideUp();
    }

    public void error(){
        mView.error();
        slideUp();
    }

    private void slideUp(){
        ViewPropertyAnimator.animate(mView).setStartDelay(1000).alpha(0f)
                .translationY(-mView.getHeight() + mTranslationY)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(300).start();
    }
}
