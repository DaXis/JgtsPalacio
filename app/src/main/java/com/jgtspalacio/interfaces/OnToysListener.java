package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public interface OnToysListener {

    void onCorrectToys(ArrayList<ToyObj> array);
    void onErrorToys(String msn);

}
