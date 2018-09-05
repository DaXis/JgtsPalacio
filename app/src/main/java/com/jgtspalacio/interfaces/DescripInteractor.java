package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public interface DescripInteractor {

    void showOptions(int option);
    void setAdapter();
    void addToy(CardObj cardObj, String sku, int cant);

}
