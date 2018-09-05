package com.jgtspalacio.interfaces;

import java.util.ArrayList;

public interface DescripView {

    void onReturn();
    void showSKU();
    void showOptions();
    void hieOptions();
    void setAdapter(ArrayList<String> spinnerArray);
    void addToCardMsn(String msn);

}
