package com.jgtspalacio.interactors;

import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.CardsInteractor;
import com.jgtspalacio.interfaces.OnCardsListener;
import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public class CardsInter implements CardsInteractor {

    private OnCardsListener listener;

    public CardsInter(OnCardsListener listener){
        this.listener = listener;
    }

    @Override
    public void setAdapter() {
        ArrayList<CardObj> array = Singleton.getBdh().getCards();
        if(array.size() > 0)
            listener.getCards(array);
        else
            listener.noGetCards("No tienes cartas");
    }
}
