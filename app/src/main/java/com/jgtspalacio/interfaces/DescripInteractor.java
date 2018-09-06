package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public interface DescripInteractor {

    void showOptions(int option);
    void setAdapter();
    void addToy(CardObj cardObj, ToyObj toyObj, String sku, int cant);
    ToyObj getToyObj(String tag, String skuId);
    void setSelectionId(ToyObj toyObj);

}
