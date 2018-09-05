package com.jgtspalacio;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000, CAMERA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        getSecureID();
        if (currentapiVersion >= Build.VERSION_CODES.M)
            checkCameraPermission();
        else
            initActivity();
    }

    private void initActivity(){
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},
                    CAMERA);
            return;
        } else {
            initActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initActivity();
                } else {
                    checkCameraPermission();
                }
                break;
        }
    }

    private void getSecureID(){
        Singleton.setSecureID(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
    }

}
