package com.jgtspalacio.interfaces;

import com.jgtspalacio.dialogs.NameDialog;
import com.jgtspalacio.objs.CardObj;

public interface MainView {

    void showNameDialog();
    void dismissNameDialog();
    void newCard();
    void goToScann(CardObj cardObj, int option);
    void goToCards();

}
