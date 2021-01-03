package com.example.oasisproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class AddItemDialog extends Dialog {
    private Context context;
    private Button buttonAdd;

    private View.OnClickListener mOnAddItem;

    public AddItemDialog(@NonNull Context context, View.OnClickListener OnAddItem) {
        super(context);
        this.context=context;
        this.mOnAddItem=OnAddItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additemdialog);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(mOnAddItem);
    }
}
