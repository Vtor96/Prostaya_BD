package com.example.prostoyspisok;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Item extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");
        setContentView(R.layout.activity_item);
    }
}