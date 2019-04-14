package com.genericgroupname.supperplannerandroid.Drink;

public class Drink {
    private String name;
    private Double sugar;
    private Double cofeine;
    private Double amount;


    public Drink(String name, Double sugar, Double cofeine, Double amount) {
        this.name = name;
        this.sugar = sugar;
        this.cofeine = cofeine;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getCofeine() {
        return cofeine;
    }

    public void setCofeine(Double cofeine) {
        this.cofeine = cofeine;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
