package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;

public interface DescripPresenter {

    void onReturn();
    void showSKU();
    void showOptions(int option);
    void setAdapter();
    void addToy(CardObj cardObj, String sku, int cant);

}
