package com.jgtspalacio.interactors;

import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.DescripInteractor;
import com.jgtspalacio.interfaces.DescripPresenter;
import com.jgtspalacio.interfaces.OnCorrectDescripListener;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public class DescripInter implements DescripInteractor {

    private OnCorrectDescripListener listener;

    public DescripInter(OnCorrectDescripListener listener){
        this.listener = listener;
    }

    @Override
    public void showOptions(int option) {
        if(option == 0){
            listener.onShowOptions();
        } else if (option == 1){
            listener.onHideOptions();
        }
    }

    @Override
    public void setAdapter() {
        ArrayList<String> array = new ArrayList<>();
        for(int i = 1; i < 11; i++){
            array.add(String.valueOf(i));
        }
        listener.onSetAdapter(array);
    }

    @Override
    public void addToy(CardObj cardObj, String sku, int cant) {
        ToyObj toyObj = new ToyObj();
        toyObj.descrip = "Descripción";
        toyObj.img_url = "url";
        toyObj.sku = sku;
        toyObj.tag = cardObj.tag;
        toyObj.toy = "Juguete";
        toyObj.cant = cant;
        if(Singleton.getBdh().insertToToys(toyObj) > 0)
            listener.onCorrectToy("Tu jeguete con SKU "+sku+" se ha agregado a tu carta");
        else
            listener.onErrorToy("Tu juguete con SKU "+sku+" ha tenido un problema y no fue agregado a tu carta");
    }
}
