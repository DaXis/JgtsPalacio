package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.ToyObj;

import java.io.File;
import java.util.ArrayList;

public interface OnToysListener {

    void onCorrectToys(ArrayList<ToyObj> array);
    void onErrorToys(String msn);
    void onCorrectAdapterUpdate(ArrayList<ToyObj> array);
    void onQRGenerate(File file);

}
