package com.jgtspalacio;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jgtspalacio.db.DBHelper;
import com.jgtspalacio.dialogs.CustomDialog;
import com.jgtspalacio.dialogs.LoadDialog;
import java.io.File;

public class Singleton extends Application {

    private static Singleton m_Instance;
    private static Context context;
    private static AppCompatActivity currentActivity;
    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;
    private static SQLiteDatabase db;
    private static DBHelper dbh;
    private static Fragment currentFrag;
    private static File cache;
    private static LoadDialog load;
    private static String secureID;
    private static CustomDialog customDialog;

    public Singleton() {
        super();
        m_Instance = this;
    }

    public static Singleton getInstance() {
        if(m_Instance == null) {
            synchronized(Singleton.class) {
                if(m_Instance == null) new Singleton();
            }
        }
        return m_Instance;
    }

    public void onCreate() {
        super.onCreate();
        context = this;
        initBD();
        genCacheDataCarpet();
        initPreferences();
    }

    public static boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    private void genCacheDataCarpet(){
        if(cache == null)
            cache = context.getFilesDir();
        if (!cache.exists()) {
            cache.mkdirs();
        }
    }

    public static File getCacheCarpet(){
        return cache;
    }

    private void initPreferences(){
        if(settings == null)
            settings = getSharedPreferences("plc_prefs", Context.MODE_PRIVATE);
        if(editor == null)
            editor = settings.edit();
    }

    public static SharedPreferences getSettings(){
        return settings;
    }

    public static SharedPreferences.Editor getEditor(){
        return editor;
    }

    private void initBD(){
        if(dbh == null)
            dbh = new DBHelper(this, "plc_jgts", null, 11);
        if(db == null)
            db = dbh.getWritableDatabase();
    }

    public static SQLiteDatabase getDb(){
        return db;
    }

    public static DBHelper getBdh(){
        return dbh;
    }

    public static int dpTpPx(Context context, int dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context, int px){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static void setCurrentFragment(Fragment arg){
        currentFrag = arg;
    }

    public static Fragment getCurrentFragment(){
        return currentFrag;
    }

    public static void setCurrenActivity(AppCompatActivity arg){
        currentActivity = arg;
    }

    public static AppCompatActivity getCurrentActivity(){
        return currentActivity;
    }

    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null)
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void savePreferences(String tag, String arg){
        editor.putString(tag, arg);
        editor.apply();
    }

    public static void savePreferences(String tag, int arg){
        editor.putInt(tag, arg);
        editor.apply();
    }

    public static void savePreferences(String tag, boolean arg){
        editor.putBoolean(tag, arg);
        editor.apply();
    }

    public static void savePreferences(String tag, long arg){
        editor.putLong(tag, arg);
        editor.apply();
    }

    public static void genToast(final String msn){
        currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(currentActivity, "" + msn, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static LoadDialog showLoadDialog(FragmentManager manager){
        if(load == null){
            synchronized (Singleton.class){
                if(load == null){
                    load = LoadDialog.newInstance();
                    load.setCancelable(false);
                    try{
                        load.show(manager, "load dialog");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return load;
    }

    public static void dissmissLoad(){
        try {
            if(load != null) {
                try{
                    load.dismiss();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                load = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setSecureID(String arg){
        secureID = arg;
    }

    public static String getSecureID(){
        return secureID;
    }

    public static CustomDialog showCustomDialog(FragmentManager manager, String title, String body, String action,
                                                int actionId){
        if(customDialog == null){
            synchronized (Singleton.class){
                if(customDialog == null){
                    customDialog = customDialog.newInstance(title, body, action, actionId);
                    customDialog.setCancelable(false);
                    try{
                        customDialog.show(manager, "custom dialog");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        customDialog = null;
                    }
                }
            }
        }
        return customDialog;
    }

    public static void dissmissCustom(){
        try {
            if(customDialog != null) {
                try{
                    customDialog.dismiss();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                customDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
