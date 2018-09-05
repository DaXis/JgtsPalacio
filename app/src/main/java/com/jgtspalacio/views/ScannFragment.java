package com.jgtspalacio.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.ScannPresenter;
import com.jgtspalacio.interfaces.ScannView;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.presenters.ScannPres;

public class ScannFragment extends Fragment implements ScannView {

    private CodeScanner mCodeScanner;
    private ScannPresenter scannPresenter;
    private CardObj cardObj;
    private int lay, option;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        cardObj = (CardObj)bundle.getSerializable("CardObj");
        lay = bundle.getInt("lay");
        option = bundle.getInt("option");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.scann_frag, container, false);

        CodeScannerView scannerView = rootView.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(Singleton.getCurrentActivity(), scannerView);
        scannPresenter = new ScannPres(this);
        scannPresenter.goToScann(mCodeScanner);

        return rootView;
    }

    @Override
    public void onCorrect(String sku) {
        DescripFragment descripFragment = new DescripFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("lay", lay);
        bundle.putString("sku", sku);
        bundle.putInt("option", option);
        bundle.putSerializable("cardObj", cardObj);
        descripFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(lay, descripFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onError() {

    }

    @Override
    public void showMessage(final String msn) {
        Singleton.genToast(msn);
    }
}
