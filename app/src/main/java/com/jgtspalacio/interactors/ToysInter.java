package com.jgtspalacio.interactors;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jgtspalacio.R;
import com.jgtspalacio.Singleton;
import com.jgtspalacio.interfaces.OnToysListener;
import com.jgtspalacio.interfaces.ToysInteractor;
import com.jgtspalacio.objs.CardObj;
import com.jgtspalacio.objs.ToyObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ToysInter implements ToysInteractor {

    private OnToysListener listener;

    public ToysInter(OnToysListener listener){
        this.listener = listener;
    }

    @Override
    public void setAdapter(String tag) {
        ArrayList<ToyObj> array = Singleton.getBdh().getToys(tag);
        if(array.size() > 0)
            listener.onCorrectToys(array);
        else
            listener.onErrorToys("Sin juguetes en esta lista");
    }

    @Override
    public void onClick(View view, int position, ArrayList<ToyObj> array) {
        switch(view.getId()){
            case R.id.mas:
                int cant = array.get(position).cant+1;
                if(Singleton.getBdh().changes(array.get(position).tag, array.get(position).sku, cant) > 0){
                    ToyObj toyObj = array.get(position);
                    toyObj.cant = cant;
                    listener.onCorrectAdapterUpdate(array);
                }
                break;
            case R.id.menos:
                int cant0 = array.get(position).cant-1;
                if(cant0 > 0){
                    if(Singleton.getBdh().changes(array.get(position).tag, array.get(position).sku, cant0) > 0){
                        ToyObj toyObj = array.get(position);
                        toyObj.cant = cant0;
                        listener.onCorrectAdapterUpdate(array);
                    }
                } else {
                    if(Singleton.getBdh().deleteToy(array.get(position)) > 0) {
                        listener.onErrorToys("Se ha borrado el jueguete SKU" + array.get(position).sku + " de tu carta.");
                        array.remove(position);
                        listener.onCorrectAdapterUpdate(array);
                    }
                }
                break;
            default:
                Log.d("click view", "click view");
                break;
        }
    }

    @Override
    public void generateQR(String tag) {
        String txt = getQRData(tag);
        Bitmap bitmap = null;
        try {
            bitmap = TextToImageEncode(txt);
            File file = saveImage(bitmap, tag);
            listener.onQRGenerate(file);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMail(CardObj cardObj) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        String subject = "Carta de "+cardObj.name;
        StringBuilder body = new StringBuilder("Yoddle");
        body.append('\n').append('\n');
        body.append(getCarta(cardObj.tag, cardObj.name)).append('\n').append('\n');
        // sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "" });
        sendIntent.putExtra(Intent.EXTRA_TEXT, body.toString());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.setType("message/rfc822");
        Singleton.getCurrentActivity().startActivity(sendIntent);
    }

    private String getCarta(String tag, String name){
        ArrayList<ToyObj> array = Singleton.getBdh().getToys(tag);
        String body = "Querido xxxxx, en esta navidad quiero pedirte: \n";
        for(ToyObj toyObj:array){
            body = body+toyObj.cant+" de "+toyObj.toy+" SKU"+toyObj.sku+"\n";
        }
        body = body+"Esperando que pueds trarme todos mis regalos, te quiere "+name;
        return body;
    }

    private String getQRData(String tag){
        try {
            ArrayList<ToyObj> array = Singleton.getBdh().getToys(tag);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tag", tag);
            JSONArray jsonArray = new JSONArray();
            for(ToyObj toyObj:array){
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("sku", toyObj.sku);
                jsonObject1.put("toy", toyObj.toy);
                jsonObject1.put("descrip", toyObj.descrip);
                jsonObject1.put("cant", toyObj.cant);
                jsonArray.put(jsonObject1);
            }
            tag = tag.replace(" ", "_");
            jsonObject.put(tag, jsonArray);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private File saveImage(Bitmap myBitmap, String tag) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        tag = tag.replace(" ", "_");
        try {
            File f = new File(Singleton.getCacheCarpet(), tag+".jpg");
            f.createNewFile();   //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(Singleton.getCurrentActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            return f;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;

    }
    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    500, 500, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        Singleton.getCurrentActivity().getResources().getColor(R.color.black):
                        Singleton.getCurrentActivity().getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

}
