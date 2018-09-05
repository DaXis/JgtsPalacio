package com.jgtspalacio;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.jgtspalacio.views.MainFragment;
import com.jgtspalacio.views.ScannFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout main_content;
    //private ScannFragment scannFragment;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Singleton.setCurrenActivity(this);
        initViews();
    }

    private void initViews(){
        main_content = (FrameLayout)findViewById(R.id.main_content);
        //scannFragment = new ScannFragment();
        //initScannFragment();
        mainFragment = new MainFragment();
        initMainFragment();
    }

    private void removeFragments(){
        if(Singleton.getCurrentFragment() != null){
            Log.d("fragment", Singleton.getCurrentFragment().getClass().toString());
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(Singleton.getCurrentFragment()).commit();
        }
    }

    private void initMainFragment(){
        if(Singleton.getCurrentFragment() != mainFragment){
            removeFragments();
            Bundle bundle = new Bundle();
            bundle.putString("name", "name_demo");
            bundle.putInt("lay", main_content.getId());
            if(mainFragment.getArguments() == null)
                mainFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(main_content.getId(), mainFragment).commit();
        }
    }

    /*private void initScannFragment(){
        if(Singleton.getCurrentFragment() != scannFragment){
            removeFragments();
            Bundle bundle = new Bundle();
            bundle.putString("name", "name_demo");
            if(scannFragment.getArguments() == null)
                scannFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(main_content.getId(), scannFragment).commit();
        }
    }*/

}
