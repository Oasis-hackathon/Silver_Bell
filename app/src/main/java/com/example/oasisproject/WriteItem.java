package com.example.oasisproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WriteItem extends AppCompatActivity {

    private AddItemDialog addItemDialog;
    private EditText editTextName, editTextMoney, editTextNumber, editTextDay;
    private ImageButton addItem;
    private Button done;
    ArrayList<ItemData> ItemList;
    DbOpenHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputitemlist);

        this.InitializeItemDate();

        init_table();

        ListView ListViewItem = (ListView) findViewById(R.id.ListViewItem);
        final ItemAdapter itemAdapter = new ItemAdapter(this, ItemList);

        ListViewItem.setAdapter(itemAdapter);

        addItem = (ImageButton) findViewById(R.id.imageButtonAddItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog = new AddItemDialog(WriteItem.this, OnAddItem);

                addItemDialog.setCanceledOnTouchOutside(true);
                addItemDialog.setCancelable(true);
                addItemDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                addItemDialog.show();
            }

            private View.OnClickListener OnAddItem = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editTextName = (EditText) addItemDialog.findViewById(R.id.editTextName);
                    editTextMoney = (EditText) addItemDialog.findViewById(R.id.editTextMoney);
                    editTextNumber = (EditText) addItemDialog.findViewById(R.id.editTextNumber);
                    editTextDay = (EditText) addItemDialog.findViewById(R.id.editTextDay);

                    String name = editTextName.getText().toString();
                    int money = Integer.parseInt(editTextMoney.getText().toString());
                    int number = Integer.parseInt(editTextNumber.getText().toString());
                    int year = Integer.parseInt(editTextMoney.getText().toString());
                    int month = Integer.parseInt(editTextMoney.getText().toString());
                    int day = Integer.parseInt(editTextDay.getText().toString());

                    ItemList.add(new ItemData(name, money, number, year, month, day));
                    addItemDialog.dismiss();
                }

            };

        });

        done = (Button) findViewById(R.id.buttonDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatas();
                showDatas();
                finish();
            }
        });
    }

    private void init_table(){
        dbHelper = new DbOpenHelper(getApplicationContext());
    }

    private void InitializeItemDate(){
        ItemList = new ArrayList<ItemData>();

        //ItemList.add(new ItemData("당근", 10000,2,2021,1,1));
        //ItemList.add(new ItemData("양파", 1500,1,2021,1,1));
        //ItemList.add(new ItemData("계란", 5000,30,2021,1,1));
    }

    private void saveDatas(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for(int i = 0;i<ItemList.size();i++){
            String name =ItemList.get(i).getName();
            int money = ItemList.get(i).getMoney();
            int number = ItemList.get(i).getNumber();
            int year = ItemList.get(i).getYear();
            int month = ItemList.get(i).getMonth();
            int day = ItemList.get(i).getDay();


            String sqlInsert = ItemDataBases.INSERT +
                    " (" +
                    "'" + name + "', " +
                    money + ", " +
                    number + ", " +
                    year + ", " +
                    month + ", " +
                    day +
                    ")";

            db.execSQL(sqlInsert);
            Log.v("알림", "데이터 입력");
        }
    }

    private void showDatas(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(ItemDataBases.SELECT, null) ;

        if (cursor.moveToFirst()) {

            String name = cursor.getString(0) ;

            Log.v("알림", "데이터 출력" + name);



        }
    }
}