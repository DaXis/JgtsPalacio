package com.jgtspalacio.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.adapters.ToysRecyclerAdapter;
import com.jgtspalacio.interfaces.OnItemClick;
import com.jgtspalacio.interfaces.ToysPresenter;
import com.jgtspalacio.interfaces.ToysView;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;
import com.jgtspalacio.presenters.ToysPres;

import java.util.ArrayList;

public class ToysFragment extends Fragment implements ToysView {

    private int lay;
    private RecyclerView toysList;
    private ToysRecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private CardObj cardObj;
    private ToysPresenter toysPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        lay = bundle.getInt("lay");
        cardObj = (CardObj)bundle.getSerializable("cardObj");
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
        View rootView = inflater.inflate(R.layout.toys_frag, container, false);

        toysPresenter = new ToysPres(this);
        toysList = (RecyclerView)rootView.findViewById(R.id.toysList);
        toysPresenter.setAdapter(cardObj.tag);

        return rootView;
    }

    @Override
    public void setAdapter(ArrayList<ToyObj> array) {
        adapter = new ToysRecyclerAdapter(array, getFragmentManager());
        mLayoutManager = new LinearLayoutManager(Singleton.getCurrentActivity());
        toysList.setLayoutManager(mLayoutManager);
        toysList.setHasFixedSize(true);
        toysList.setItemAnimator(new DefaultItemAnimator());
        toysList.setAdapter(adapter);
        adapter.SetOnItemClickListener(new OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void showMsn(String msn) {
        Singleton.genToast(msn);
    }
}
