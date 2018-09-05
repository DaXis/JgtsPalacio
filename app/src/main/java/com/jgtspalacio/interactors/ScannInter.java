package com.jgtspalacio.interactors;

import android.support.annotation.NonNull;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.jgtspalacio.interfaces.OnScannerListener;
import com.jgtspalacio.interfaces.ScannInteractor;

public class ScannInter implements ScannInteractor {

    private OnScannerListener listener;

    public ScannInter(OnScannerListener listener){
        this.listener = listener;
    }

    @Override
    public void goToScann(CodeScanner mCodeScanner) {
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                listener.onScann(result.getText());
            }
        });
    }

}
