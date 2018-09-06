package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.ToyObj;

import java.io.File;
import java.util.ArrayList;

public interface ToysView {

    void setAdapter(ArrayList<ToyObj> array);
    void showMsn(String msn);
    void updateAdapter(ArrayList<ToyObj> array);
    void goToScann();
    void showCardName(String name);
    void showQRCode(File file);
    void showLoadDialog();
    void hideLoadDialog();
}
