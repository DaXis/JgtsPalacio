package com.jgtspalacio.interactors;

import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.OnToysListener;
import com.jgtspalacio.interfaces.ToysInteractor;
import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public class ToysInter implements ToysInteractor {

    private OnToysListener listener;

    public ToysInter(OnToysListener listener){
        this.listener = listener;
    }

    @Override
    public void setAdapter(String tag) {
        ArrayList<ToyObj> array = Singleton.getBdh().getToys(tag);
        if(array.size() > 0)
            listener.onCorrectToys(array);
        else
            listener.onErrorToys("Sin juguetes en esta lista");
    }
}
