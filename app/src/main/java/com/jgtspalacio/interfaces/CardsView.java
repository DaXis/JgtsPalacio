package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public interface CardsView {

    void setAdapter(ArrayList<CardObj> array);
    void showMsn(String msn);
    void goToToysList(CardObj cardObj);

}
