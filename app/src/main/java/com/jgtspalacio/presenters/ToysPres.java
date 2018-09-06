package com.jgtspalacio.presenters;

import android.view.View;

import com.jgtspalacio.interactors.ToysInter;
import com.jgtspalacio.interfaces.OnToysListener;
import com.jgtspalacio.interfaces.ToysInteractor;
import com.jgtspalacio.interfaces.ToysPresenter;
import com.jgtspalacio.interfaces.ToysView;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

import java.io.File;
import java.util.ArrayList;

public class ToysPres implements ToysPresenter, OnToysListener {

    private ToysView toysView;
    private ToysInteractor toysInteractor;

    public ToysPres(ToysView toysView){
        this.toysView = toysView;
        toysInteractor = new ToysInter(this);
    }

    @Override
    public void setAdapter(String tag) {
        toysInteractor.setAdapter(tag);
    }

    @Override
    public void onClick(View view, int position, ArrayList<ToyObj> array) {
        toysInteractor.onClick(view, position, array);
    }

    @Override
    public void goToScann() {
        toysView.goToScann();
    }

    @Override
    public void showCardName(String name) {
        toysView.showCardName(name);
    }

    @Override
    public void generateQR(String tag) {
        toysView.showLoadDialog();
        toysInteractor.generateQR(tag);
    }

    @Override
    public void sendMail(CardObj cardObj) {
        toysInteractor.sendMail(cardObj);
    }

    @Override
    public void onCorrectToys(ArrayList<ToyObj> array) {
        toysView.setAdapter(array);
    }

    @Override
    public void onErrorToys(String msn) {
        toysView.showMsn(msn);
    }

    @Override
    public void onCorrectAdapterUpdate(ArrayList<ToyObj> array) {
        toysView.updateAdapter(array);
    }

    @Override
    public void onQRGenerate(File file) {
        toysView.hideLoadDialog();
        toysView.showQRCode(file);
    }

}
