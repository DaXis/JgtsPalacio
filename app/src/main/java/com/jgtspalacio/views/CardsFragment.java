package com.jgtspalacio.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.adapters.CardsRecyclerAdapter;
import com.jgtspalacio.interfaces.CardsPresenter;
import com.jgtspalacio.interfaces.CardsView;
import com.jgtspalacio.interfaces.OnItemClick;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.presenters.CardsPres;
import com.jgtspalacio.presenters.ScannPres;

import java.util.ArrayList;

public class CardsFragment extends Fragment implements CardsView {

    private int lay;
    private RecyclerView cardsList;
    private CardsRecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private CardsPresenter cardsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        lay = bundle.getInt("lay");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cards_frag, container, false);

        cardsPresenter = new CardsPres(this);
        cardsList = (RecyclerView)rootView.findViewById(R.id.cardsList);
        cardsPresenter.setAdapter();

        return rootView;
    }

    @Override
    public void setAdapter(ArrayList<CardObj> array) {
        adapter = new CardsRecyclerAdapter(array, getFragmentManager());
        mLayoutManager = new LinearLayoutManager(Singleton.getCurrentActivity());
        cardsList.setLayoutManager(mLayoutManager);
        cardsList.setHasFixedSize(true);
        cardsList.setItemAnimator(new DefaultItemAnimator());
        cardsList.setAdapter(adapter);
        adapter.SetOnItemClickListener(new OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                cardsPresenter.goToToysList(adapter.getItem(position));
            }
        });
    }

    @Override
    public void showMsn(String msn) {
        Singleton.genToast(msn);
    }

    @Override
    public void goToToysList(CardObj cardObj) {
        ToysFragment toysFragment = new ToysFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("lay", lay);
        bundle.putSerializable("cardObj", cardObj);
        toysFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(lay, toysFragment)
                .addToBackStack(null)
                .commit();
    }
}
