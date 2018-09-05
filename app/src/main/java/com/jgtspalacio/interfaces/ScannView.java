package com.jgtspalacio.interfaces;

public interface ScannView {

    void onCorrect(String sku);
    void onError();
    void showMessage(String msn);

}
