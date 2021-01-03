package com.example.oasisproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class clickRefrigerator extends AppCompatActivity {
    LayoutInflater inflater;
    LinearLayout container;
    Button sort;
    Button full;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clickrefrigerator);


        full = (Button)findViewById(R.id.buttonSeeFull);

    }

}
