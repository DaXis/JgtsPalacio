package com.jgtspalacio.interfaces;

import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public interface ToysView {

    void setAdapter(ArrayList<ToyObj> array);
    void showMsn(String msn);
}
