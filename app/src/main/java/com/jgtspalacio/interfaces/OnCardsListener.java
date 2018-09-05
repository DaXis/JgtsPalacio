package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public interface OnCardsListener {

    void getCards(ArrayList<CardObj> array);
    void noGetCards(String msn);

}
