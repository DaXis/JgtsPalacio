package com.jgtspalacio.interfaces;

import android.view.View;

import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

public interface ToysPresenter {

    void setAdapter(String tag);
    void onClick(View view, int position, ArrayList<ToyObj> array);
    void goToScann();
    void showCardName(String name);
    void generateQR(String tag);
    void sendMail(CardObj cardObj);

}
