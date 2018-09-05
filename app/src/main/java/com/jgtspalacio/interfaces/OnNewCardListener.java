package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.CardObj;

public interface OnNewCardListener {

    void onNewCardCorrect(CardObj cardObj, int option);
    void onNewCardError(String name);

}
