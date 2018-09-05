package com.jgtspalacio.interactors;

import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.MainInterator;
import com.jgtspalacio.interfaces.OnNewCardListener;
import com.jgtspalacio.objs.CardObj;

public class MainInter implements MainInterator {

    private OnNewCardListener listener;

    public MainInter(OnNewCardListener listener){
        this.listener = listener;
    }

    @Override
    public void newCard(String name, int option) {
        CardObj cardObj = new CardObj();
        cardObj.tag = Singleton.getSecureID()+"_"+name;
        cardObj.name = name;
        if(Singleton.getBdh().inserToCards(cardObj) > 0)
            listener.onNewCardCorrect(cardObj, option);
        else
            listener.onNewCardError(name);
    }
}
