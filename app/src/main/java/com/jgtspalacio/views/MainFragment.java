package com.jgtspalacio.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.dialogs.NameDialog;
import com.jgtspalacio.interfaces.MainPresenter;
import com.jgtspalacio.interfaces.MainView;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.presenters.MainPres;

public class MainFragment extends Fragment implements MainView, View.OnClickListener {

    private ImageView btn_scann, new_card, cards;
    private MainPresenter mainPresenter;
    private NameDialog nameDialog;
    private int lay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mainPresenter = new MainPres(this, getFragmentManager());
        lay = bundle.getInt("lay");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume(){
        super.onResume();
        Singleton.setCurrentFragment(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_frag, container, false);

        btn_scann = (ImageView)rootView.findViewById(R.id.btn_scann);
        btn_scann.setOnClickListener(this);
        new_card = (ImageView)rootView.findViewById(R.id.new_card);
        new_card.setOnClickListener(this);
        cards = (ImageView)rootView.findViewById(R.id.cards);
        cards.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_scann:
                mainPresenter.goToScann(1);
                break;
            case R.id.new_card:
                mainPresenter.newName();
                break;
            case R.id.cards:
                mainPresenter.goToCards();
                break;
        }
    }

    @Override
    public void showNameDialog() {
        nameDialog = NameDialog.newInstance(mainPresenter);
        nameDialog.setCancelable(true);
        nameDialog.show(getFragmentManager(), "name dialog");
    }

    @Override
    public void dismissNameDialog() {
        nameDialog.dismiss();
    }

    @Override
    public void newCard() {

    }

    @Override
    public void goToScann(CardObj cardObj, int option) {
        ScannFragment scannFragment = new ScannFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("CardObj", cardObj);
        bundle.putInt("option", option);
        bundle.putInt("lay", lay);
        scannFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(lay, scannFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToCards() {
        CardsFragment cardsFragment = new CardsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("lay", lay);
        cardsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(lay, cardsFragment)
                .addToBackStack(null)
                .commit();
    }
}
