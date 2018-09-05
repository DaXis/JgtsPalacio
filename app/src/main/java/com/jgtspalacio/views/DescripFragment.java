package com.jgtspalacio.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.DescripPresenter;
import com.jgtspalacio.interfaces.DescripView;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.presenters.DescripPres;

import java.util.ArrayList;

public class DescripFragment extends Fragment implements View.OnClickListener, DescripView{

    private String sku;
    private int lay, option;
    private TextView sku_txt;
    private Spinner spinner;
    private ImageView refresh;
    private Button add_btn;
    private DescripPresenter descripPresenter;
    private ArrayAdapter<String> adapter;
    private CardObj cardObj;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        sku = bundle.getString("sku");
        lay = bundle.getInt("lay");
        option = bundle.getInt("option");
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
        View rootView = inflater.inflate(R.layout.desc_frag, container, false);

        descripPresenter = new DescripPres(this);

        sku_txt = (TextView)rootView.findViewById(R.id.sku);
        descripPresenter.showSKU();

        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        descripPresenter.setAdapter();

        refresh = (ImageView)rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        add_btn = (Button)rootView.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        descripPresenter.showOptions(option);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.refresh:
                descripPresenter.onReturn();
                break;
            case R.id.add_btn:
                descripPresenter.addToy(cardObj, sku, Integer.parseInt(spinner.getSelectedItem().toString()));
                break;
        }
    }

    @Override
    public void onReturn() {
        Singleton.getCurrentActivity().onBackPressed();
    }

    @Override
    public void showSKU() {
        sku_txt.setText("SKU "+sku);
    }

    @Override
    public void showOptions() {
        add_btn.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hieOptions() {
        add_btn.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(ArrayList<String> spinnerArray) {
        adapter = new ArrayAdapter<String>(Singleton.getCurrentActivity(),
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void addToCardMsn(String msn) {
        Singleton.genToast(msn);
    }

}
