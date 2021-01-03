package com.example.oasisproject;

public class FullItemData{
    private String name;
    private String until;
    private int number;


    public FullItemData(String name, int number, String until){
        this.name =name;
        this.number=number;
        this.until=until;
    }


    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getUntil(){return until;}
}
