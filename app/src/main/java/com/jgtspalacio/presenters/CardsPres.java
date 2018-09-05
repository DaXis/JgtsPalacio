package com.jgtspalacio.presenters;

import com.jgtspalacio.interactors.CardsInter;
import com.jgtspalacio.interfaces.CardsInteractor;
import com.jgtspalacio.interfaces.CardsPresenter;
import com.jgtspalacio.interfaces.CardsView;
import com.jgtspalacio.interfaces.OnCardsListener;
import com.jgtspalacio.objs.CardObj;

import java.util.ArrayList;

public class CardsPres implements CardsPresenter, OnCardsListener{

    private CardsView cardsView;
    private CardsInteractor cardsInteractor;

    public CardsPres(CardsView cardsView){
        this.cardsView = cardsView;
        cardsInteractor = new CardsInter(this);
    }

    @Override
    public void setAdapter() {
        cardsInteractor.setAdapter();
    }

    @Override
    public void goToToysList(CardObj cardObj) {
        cardsView.goToToysList(cardObj);
    }

    @Override
    public void getCards(ArrayList<CardObj> array) {
        cardsView.setAdapter(array);
    }

    @Override
    public void noGetCards(String msn) {

    }
}
