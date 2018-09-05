package com.jgtspalacio.presenters;

import com.jgtspalacio.interactors.ToysInter;
import com.jgtspalacio.interfaces.OnToysListener;
import com.jgtspalacio.interfaces.ToysInteractor;
import com.jgtspalacio.interfaces.ToysPresenter;
import com.jgtspalacio.interfaces.ToysView;
import com.jgtspalacio.objs.ToyObj;

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
    public void onCorrectToys(ArrayList<ToyObj> array) {
        toysView.setAdapter(array);
    }

    @Override
    public void onErrorToys(String msn) {
        toysView.showMsn(msn);
    }
}
