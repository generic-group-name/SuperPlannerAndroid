package com.genericgroupname.supperplannerandroid.Drink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrinkManager {
    private Double sugarIncome;
    private Double cafeineIncome;
    private Double waterVolume;
    private ArrayList<Drink> drunkDrunks = new ArrayList<>();
    private ArrayList<Drink> dri = new ArrayList<>();

    public DrinkManager() {
        dri.add(new Drink("coffee",0.0,70.0,100.0));
        dri.add(new Drink("water",0.0,0.0,200.0));
        dri.add(new Drink("cola",10.6,8.0,100.0));
        sugarIncome=0.0;
        cafeineIncome=0.0;
        waterVolume=0.0;

    }

    public Double getSugarIncome() {
        return sugarIncome;
    }

    public Double getCafeineIncome() {
        return cafeineIncome;
    }

    public Double getWaterVolume() {
        return waterVolume;
    }

    public ArrayList<Drink> getDrunkDrunks() {
        return drunkDrunks;
    }

    public ArrayList<Drink> getDri() {
        return dri;
    }
    public Double getAmount(){
        double sum = 0.0;
        for (Drink d:drunkDrunks) {
            sum+=d.getAmount();
        }
        return sum;
    }
    public Double getCofeine(){
        double sum = 0.0;
        for (Drink d:drunkDrunks) {
            sum+=d.getAmount()/100*d.getCofeine();
        }
        return sum;

    }
    public Double getSugar(){
        double sum = 0.0;
        for (Drink d:drunkDrunks) {
            sum+=d.getAmount()/100*d.getSugar();
        }
        return sum;

    }
}

