package com.jgtspalacio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

import java.util.ArrayList;

/**
 * Created by daxis on 13/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private Context ctx;

    final String sqlCreate0 = "CREATE TABLE Cards (id INTEGER PRIMARY KEY AUTOINCREMENT, tag TEXT UNIQUE, name TEXT)";
    final String sqlCreate1 = "CREATE TABLE Toys (id INTEGER, tag TEXT, toy TEXT, sku TEXT, descrip TEXT, img_ur TEXT, cant INTEGER)";
    final String[] create = {sqlCreate0,sqlCreate1};

    final private static String id = "id";
    final private static String tag = "tag";
    final private static String name = "name";
    final private static String toy = "toy";
    final private static String sku = "sku";
    final private static String descrip = "descrip";
    final private static String img_url = "img_ur";
    final private static String cant = "cant";

    final private static String CARDS = "Cards";
    final private static String TOYS = "Toys";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i = 0; i < create.length; i++){
            db.execSQL(create[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long inserToCards(CardObj cardObj){
        ContentValues values = new ContentValues();
        values.put(tag, cardObj.tag);
        values.put(name, cardObj.name);
        return Singleton.getDb().insert(CARDS, null, values);
    }

    public long insertToToys(ToyObj toyObj){
        ContentValues values = new ContentValues();
        values.put(id, toyObj.id);
        values.put(tag, toyObj.tag);
        values.put(toy, toyObj.toy);
        values.put(sku, toyObj.sku);
        values.put(descrip, toyObj.descrip);
        values.put(img_url, toyObj.img_url);
        values.put(cant, toyObj.cant);
        return Singleton.getDb().insert(TOYS, null, values);
    }

    public ArrayList<CardObj> getCards(){
        ArrayList<CardObj> array = new ArrayList<>();

        String[] c = new String[]{id, tag, name};
        Cursor cursor = Singleton.getDb().query(CARDS, c, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            CardObj cardObj = new CardObj();
            cardObj.id = cursor.getInt(0);
            cardObj.tag = cursor.getString(1);
            cardObj.name = cursor.getString(2);
            array.add(cardObj);
            cursor.moveToNext();
        }

        return array;
    }

    public ArrayList<ToyObj> getToys(int id){
        ArrayList<ToyObj> array = new ArrayList<>();

        String[] c = new String[]{this.id, tag, toy, sku, descrip, img_url, cant};
        String[] args = {String.valueOf(id)};
        String w = this.id + "=?";
        Cursor cursor = Singleton.getDb().query(TOYS, c, w, args, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ToyObj toyObj = new ToyObj();
            toyObj.id = cursor.getInt(0);
            toyObj.tag = cursor.getString(1);
            toyObj.toy = cursor.getString(2);
            toyObj.sku = cursor.getString(3);
            toyObj.descrip = cursor.getString(4);
            toyObj.img_url = cursor.getString(5);
            toyObj.cant = cursor.getInt(6);
            array.add(toyObj);
            cursor.moveToNext();
        }

        return array;
    }

    public ArrayList<ToyObj> getToys(String tag){
        ArrayList<ToyObj> array = new ArrayList<>();

        String[] c = new String[]{id, this.tag, toy, sku, descrip, img_url, cant};
        String[] args = {tag};
        String w = this.tag + "=?";
        Cursor cursor = Singleton.getDb().query(TOYS, c, w, args, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ToyObj toyObj = new ToyObj();
            toyObj.id = cursor.getInt(0);
            toyObj.tag = cursor.getString(1);
            toyObj.toy = cursor.getString(2);
            toyObj.sku = cursor.getString(3);
            toyObj.descrip = cursor.getString(4);
            toyObj.img_url = cursor.getString(5);
            toyObj.cant = cursor.getInt(6);
            array.add(toyObj);
            cursor.moveToNext();
        }

        return array;
    }

    public ToyObj getToy(String tag, String skuId){
        String[] c = new String[]{id, this.tag, toy, sku, descrip, img_url, cant};
        String[] args = {tag, skuId};
        String w = this.tag + "=? AND "+sku+"=?";
        Cursor cursor = Singleton.getDb().query(TOYS, c, w, args, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        ToyObj toyObj = null;
        while (!cursor.isAfterLast()) {
            toyObj = new ToyObj();
            toyObj.id = cursor.getInt(0);
            toyObj.tag = cursor.getString(1);
            toyObj.toy = cursor.getString(2);
            toyObj.sku = cursor.getString(3);
            toyObj.descrip = cursor.getString(4);
            toyObj.img_url = cursor.getString(5);
            toyObj.cant = cursor.getInt(6);
            cursor.moveToNext();
        }

        return toyObj;
    }

    public static int changes(String arg1, String arg2, int arg3) {
        ContentValues values = new ContentValues();
        values.put(cant, arg3);
        String[] args = {arg1, arg2};
        String w = tag + "=? AND "+sku+"=?";
        return Singleton.getDb().update(TOYS, values, w, args);
    }

    public static int deleteToy(ToyObj obj) {
        String[] args = {obj.tag, obj.sku};
        String w = tag + "=? AND "+sku+"=?";
        return Singleton.getDb().delete(TOYS, w, args);
    }

}
