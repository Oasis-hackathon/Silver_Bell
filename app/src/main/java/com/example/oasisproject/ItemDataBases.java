package com.example.oasisproject;

public final class ItemDataBases {
    private ItemDataBases(){};

    public static final String TBL = "buyItem";
    public static final String NAME = "name";
    public static final String MONEY = "money";
    public static final String NUM = "number";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";

    public static final String _CREATE_TBL = "CREATE TABLE IF NOT EXISTS " + TBL + " " +
            "(" +
                NAME + " TEXT NOT NULL, " +
                MONEY + " INTEGER NOT NULL, " +
                NUM + " INTEGER NOT NULL, " +
                YEAR + " INTEGER, " +
                MONTH + " INTEGER, " +
                DAY + " INTEGER" +
            ")";

    public static final String DROP_TBL = "DROP TABLE IF EXISTS " + TBL;

    public static final String SELECT = "SELECT * FROM " + TBL;

    public static final String INSERT = "INSERT OR REPLACE INTO " + TBL + " " +
          "(" + NAME + ", " + MONEY + ", " + NUM + ", " + YEAR + ", " + MONTH + ", " + DAY + ") VALUES" ;

    public static final String DELETE = "DELETE FROM  " + TBL;
}

