package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

public interface DescripPresenter {

    void onReturn();
    void showSKU();
    void showOptions(int option);
    void setAdapter();
    void addToy(CardObj cardObj, ToyObj toyObj, String sku, int cant);
    ToyObj getToyOj(String tag, String skuId);
    void setSelectionId(ToyObj toyObj);

}
