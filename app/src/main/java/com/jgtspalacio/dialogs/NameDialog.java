package com.jgtspalacio.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jgtspalacio.R;
import com.jgtspalacio.interfaces.MainPresenter;

public class NameDialog extends DialogFragment implements View.OnClickListener {

    private Button cancel, action;
    private EditText name;
    private static MainPresenter mainPresenter;

    public static NameDialog newInstance(MainPresenter arg){
        mainPresenter = arg;
        NameDialog nameDialog = new NameDialog();
        return nameDialog;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        int style = DialogFragment.STYLE_NORMAL;
        int theme = android.R.style.Theme_Holo;
        setStyle(style, theme);
    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.name_dialog, null);
        builder.setView(v);

        name = (EditText)v.findViewById(R.id.name);

        cancel = (Button)v.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        action = (Button)v.findViewById(R.id.action);
        action.setOnClickListener(this);

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.HONEYCOMB){
            Log.e("solved super error", "solved super error OK");
        } else
            super.onSaveInstanceState(outState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:
                dismiss();
                break;
            case R.id.action:
                if(name.getText().length() > 0){
                    mainPresenter.newCard(name.getText().toString(), 0);
                    //dismiss();
                }
                break;
        }
    }
}