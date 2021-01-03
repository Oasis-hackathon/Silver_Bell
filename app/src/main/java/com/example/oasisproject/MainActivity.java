package com.example.oasisproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button myRecipe, myRefrigerator, myHouseKeeper, full;
    Button addItem;
    private static LinearLayout container;
    private static LayoutInflater inflater;
    private ArrayList<FullItemData> Fullitemdata;
    ImageButton refrigerator;
    SelectDialog selectDialog;
    DbOpenHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.main_container);

        myRecipe = (Button) findViewById(R.id.buttonRecipe);
        myRefrigerator = (Button) findViewById(R.id.buttonRefrigerator);
        myHouseKeeper = (Button) findViewById(R.id.buttonHousekeeping);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.refrigerator, container, true);

        setAddItem();

        dbHelper = new DbOpenHelper(this);

        myRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                inflater.inflate(R.layout.recipe, container, true);
            }
        });

        myRefrigerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                inflater.inflate(R.layout.refrigerator, container, true);

                setAddItem();
                ImageButton refrigerator = (ImageButton) findViewById(R.id.imageButtonRefrigerator);
                refrigerator.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        container.removeAllViews();
                        inflater.inflate(R.layout.clickrefrigerator, container, true);
                        full = (Button) findViewById(R.id.buttonSeeFull);
                        full.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                container.removeAllViews();
                                inflater.inflate(R.layout.fullitemlist, container, true);
                                Spinner sortStandard = (Spinner) findViewById(R.id.spinnerSortStandard);
                                ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.sort_standard, android.R.layout.simple_spinner_item);
                                sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sortStandard.setAdapter(sortAdapter);

                                Fullitemdata = new ArrayList<FullItemData>();

                                db = dbHelper.getReadableDatabase();
                                Cursor cursor = db.rawQuery(ItemDataBases.SELECT, null);

                                while(cursor.moveToNext()){
                                    String name = cursor.getString(0);
                                    int number = cursor.getInt(2);
                                    int year = cursor.getInt(3);
                                    int month = cursor.getInt(4);
                                    int day = cursor.getInt(5);
                                    String until = "" + year + "-" + month + "-" + day;

                                    Fullitemdata.add(new FullItemData(name, number, until));

                                }

                                ListView ListViewItem = (ListView) findViewById(R.id.listviewFull);
                                final FullItemAdapter fullItemAdapter = new FullItemAdapter(getBaseContext(), Fullitemdata);

                                ListViewItem.setAdapter(fullItemAdapter);


                            }
                        });
                    }
                });
            }
        });

        myHouseKeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                inflater.inflate(R.layout.housekeepermonth, container, true);

                Spinner monthSpinner = (Spinner) findViewById(R.id.spinner_month);
                ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.date_month, android.R.layout.simple_spinner_item);
                monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                monthSpinner.setAdapter(monthAdapter);

            }
        });

        refrigerator = (ImageButton) findViewById(R.id.imageButtonRefrigerator);
        refrigerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                inflater.inflate(R.layout.clickrefrigerator, container, true);
                full = (Button) findViewById(R.id.buttonSeeFull);
                full.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        container.removeAllViews();
                        inflater.inflate(R.layout.fullitemlist, container, true);
                        Spinner sortStandard = (Spinner) findViewById(R.id.spinnerSortStandard);
                        ArrayAdapter sortAdapter = ArrayAdapter.createFromResource(getBaseContext(), R.array.sort_standard, android.R.layout.simple_spinner_item);
                        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sortStandard.setAdapter(sortAdapter);

                        Fullitemdata = new ArrayList<FullItemData>();
                        db = dbHelper.getReadableDatabase();

                        Cursor cursor = db.rawQuery(ItemDataBases.SELECT, null);

                        while(cursor.moveToNext()){
                            String name = cursor.getString(0);
                            int number = cursor.getInt(2);
                            int year = cursor.getInt(3);
                            int month = cursor.getInt(4);
                            int day = cursor.getInt(5);
                            String until = "" + year + "-" + month + "-" + day;

                            Fullitemdata.add(new FullItemData(name, number, until));
                        }

                        ListView ListViewItem = (ListView) findViewById(R.id.listviewFull);
                        final FullItemAdapter fullItemAdapter = new FullItemAdapter(getBaseContext(), Fullitemdata);

                        ListViewItem.setAdapter(fullItemAdapter);


                    }
                });
            }
        });


    }

    void setAddItem() {
        addItem = (Button) findViewById(R.id.buttonAdditem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDialog = new SelectDialog(MainActivity.this, OnTakePicture, OnLoadPicture, OnWrite);

                selectDialog.setCanceledOnTouchOutside(true);
                selectDialog.setCancelable(true);
                selectDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                selectDialog.show();

            }

            private View.OnClickListener OnTakePicture = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "확인버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                    selectDialog.dismiss();
                }

            };

            private View.OnClickListener OnLoadPicture = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "확인버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                    selectDialog.dismiss();
                }

            };

            private View.OnClickListener OnWrite = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), WriteItem.class);
                    startActivity(intent);
                    selectDialog.dismiss();
                }

            };

        });
    }

    public static LinearLayout LinearLayoutContainer() {
        return container;
    }

    public static LayoutInflater LinearLayoutInflater() {
        return inflater;
    }
}