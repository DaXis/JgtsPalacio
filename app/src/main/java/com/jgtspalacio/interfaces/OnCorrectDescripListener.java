package com.jgtspalacio.interfaces;

import java.util.ArrayList;

public interface OnCorrectDescripListener {

    void onShowOptions();
    void onHideOptions();
    void onSetAdapter(ArrayList<String> array);
    void onCorrectToy(String msn);
    void onErrorToy(String msn);
    void onSelectionCorrect(int id);

}
