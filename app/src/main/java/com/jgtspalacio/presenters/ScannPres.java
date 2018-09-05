package com.jgtspalacio.presenters;

import com.budiyev.android.codescanner.CodeScanner;
import com.jgtspalacio.interactors.ScannInter;
import com.jgtspalacio.interfaces.OnScannerListener;
import com.jgtspalacio.interfaces.ScannInteractor;
import com.jgtspalacio.interfaces.ScannPresenter;
import com.jgtspalacio.interfaces.ScannView;

public class ScannPres implements ScannPresenter, OnScannerListener {

    private ScannView scannView;
    private ScannInteractor scannInteractor;

    public ScannPres(ScannView scannView){
        this.scannView = scannView;
        scannInteractor = new ScannInter(this);
    }


    @Override
    public void goToScann(CodeScanner mCodeScanner) {
        scannInteractor.goToScann(mCodeScanner);
    }

    @Override
    public void onScann(String result) {
        //scannView.showMessage(result);
        scannView.onCorrect(result);
    }
}
