package com.jgtspalacio.presenters;

import com.jgtspalacio.interactors.DescripInter;
import com.jgtspalacio.interfaces.DescripInteractor;
import com.jgtspalacio.interfaces.DescripPresenter;
import com.jgtspalacio.interfaces.DescripView;
import com.jgtspalacio.interfaces.OnCorrectDescripListener;
import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public class DescripPres implements DescripPresenter, OnCorrectDescripListener {

    private DescripView descripView;
    private DescripInteractor descripInteractor;

    public DescripPres(DescripView descripView){
        this.descripView = descripView;
        descripInteractor = new DescripInter(this);
    }

    @Override
    public void onReturn() {
        descripView.onReturn();
    }

    @Override
    public void showSKU() {
        descripView.showSKU();
    }

    @Override
    public void showOptions(int option) {
        descripInteractor.showOptions(option);
    }

    @Override
    public void setAdapter() {
        descripInteractor.setAdapter();
    }

    @Override
    public void addToy(CardObj cardObj, String sku, int cant) {
        descripInteractor.addToy(cardObj, sku, cant);
    }

    @Override
    public void onShowOptions() {
        descripView.showOptions();
    }

    @Override
    public void onHideOptions() {
        descripView.hieOptions();
    }

    @Override
    public void onSetAdapter(ArrayList<String> array) {
        descripView.setAdapter(array);
    }

    @Override
    public void onCorrectToy(String msn) {
        descripView.addToCardMsn(msn);
    }

    @Override
    public void onErrorToy(String msn) {
        descripView.addToCardMsn(msn);
    }
}
