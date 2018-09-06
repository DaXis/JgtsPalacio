package com.jgtspalacio.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.adapters.ToysRecyclerAdapter;
import com.jgtspalacio.dialogs.QRDialog;
import com.jgtspalacio.interfaces.OnItemClick;
import com.jgtspalacio.interfaces.ToysPresenter;
import com.jgtspalacio.interfaces.ToysView;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;
import com.jgtspalacio.presenters.ToysPres;

import java.io.File;
import java.util.ArrayList;

public class ToysFragment extends Fragment implements ToysView, View.OnClickListener {

    private int lay;
    private RecyclerView toysList;
    private ToysRecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private CardObj cardObj;
    private ToysPresenter toysPresenter;
    private LinearLayout imprimir, scannear, compartir;
    private TextView card_name;

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

        imprimir = (LinearLayout)rootView.findViewById(R.id.imprimir);
        imprimir.setOnClickListener(this);
        scannear = (LinearLayout)rootView.findViewById(R.id.scannear);
        scannear.setOnClickListener(this);
        compartir = (LinearLayout)rootView.findViewById(R.id.compartir);
        compartir.setOnClickListener(this);

        card_name = (TextView)rootView.findViewById(R.id.card_name);
        toysPresenter.showCardName(cardObj.name);

        return rootView;
    }

    @Override
    public void setAdapter(final ArrayList<ToyObj> array) {
        adapter = new ToysRecyclerAdapter(array, getFragmentManager());
        mLayoutManager = new LinearLayoutManager(Singleton.getCurrentActivity());
        toysList.setLayoutManager(mLayoutManager);
        toysList.setHasFixedSize(true);
        toysList.setItemAnimator(new DefaultItemAnimator());
        toysList.setAdapter(adapter);
        adapter.SetOnItemClickListener(new OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                toysPresenter.onClick(view, position, array);
            }
        });
    }

    @Override
    public void showMsn(String msn) {
        Singleton.genToast(msn);
    }

    @Override
    public void updateAdapter(ArrayList<ToyObj> array) {
        adapter.updateAdapter(array);
    }

    @Override
    public void goToScann() {
        ScannFragment scannFragment = new ScannFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("CardObj", cardObj);
        bundle.putInt("option", 2);
        bundle.putInt("lay", lay);
        scannFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(lay, scannFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showCardName(String name) {
        card_name.setText("Carta de "+name);
    }

    @Override
    public void showQRCode(File file) {
        QRDialog qrDialog = QRDialog.newInstance(file);
        qrDialog.setCancelable(true);
        qrDialog.show(getFragmentManager(), "qr dialog");
    }

    @Override
    public void showLoadDialog() {
        Singleton.showLoadDialog(getFragmentManager());
    }

    @Override
    public void hideLoadDialog() {
        Singleton.dissmissLoad();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imprimir:
                toysPresenter.generateQR(cardObj.tag);
                break;
            case R.id.scannear:
                toysPresenter.goToScann();
                break;
            case R.id.compartir:
                toysPresenter.sendMail(cardObj);
                break;
        }
    }
}
