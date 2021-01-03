package com.example.oasisproject;

public class ItemData{
    private String name;
    private int money;
    private int number;
    private int year;
    private int month;
    private int day;

    public ItemData(String name, int money, int number, int year, int month, int day){
        this.name =name;
        this.money=money;
        this.number=number;
        this.year = year;
        this.month=month;
        this.day=day;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMoney() {
        return money;
    }

    public int getMonth() {
        return month;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
