package com.jgtspalacio.presenters;

import android.support.v4.app.FragmentManager;

import com.jgtspalacio.Singleton;
import com.jgtspalacio.interactors.MainInter;
import com.jgtspalacio.interfaces.MainInterator;
import com.jgtspalacio.interfaces.MainPresenter;
import com.jgtspalacio.interfaces.MainView;
import com.jgtspalacio.interfaces.OnNewCardListener;
import com.jgtspalacio.objs.CardObj;

public class MainPres implements MainPresenter, OnNewCardListener {

    private MainView mainView;
    private MainInterator mainInterator;
    private FragmentManager fragmentManager;

    public MainPres(MainView mainView, FragmentManager fragmentManager){
        this.mainView = mainView;
        mainInterator = new MainInter(this);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void newName() {
        mainView.showNameDialog();
    }

    @Override
    public void newCard(String name, int option) {
        mainInterator.newCard(name, option);
    }

    @Override
    public void goToScann(int option) {
        mainView.goToScann(null, option);
    }

    @Override
    public void goToCards() {
        mainView.goToCards();
    }

    @Override
    public void onNewCardCorrect(CardObj cardObj, int option) {
        mainView.dismissNameDialog();
        mainView.goToScann(cardObj, option);
    }

    @Override
    public void onNewCardError(String name) {
        Singleton.showCustomDialog(fragmentManager, "Atención",
                "La carta de "+name+" ya existe, editala desde la lista de cartas", "Aceptar", 0);
    }
}
