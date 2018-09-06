package com.jgtspalacio.presenters;

import com.jgtspalacio.interactors.DescripInter;
import com.jgtspalacio.interfaces.DescripInteractor;
import com.jgtspalacio.interfaces.DescripPresenter;
import com.jgtspalacio.interfaces.DescripView;
import com.jgtspalacio.interfaces.OnCorrectDescripListener;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

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
    public void addToy(CardObj cardObj, ToyObj toyObj, String sku, int cant) {
        descripInteractor.addToy(cardObj, toyObj, sku, cant);
    }

    @Override
    public ToyObj getToyOj(String tag, String skuId) {
        return descripInteractor.getToyObj(tag, skuId);
    }

    @Override
    public void setSelectionId(ToyObj toyObj) {
        descripInteractor.setSelectionId(toyObj);
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

    @Override
    public void onSelectionCorrect(int id) {
        descripView.setCantSpinner(id);
    }
}
