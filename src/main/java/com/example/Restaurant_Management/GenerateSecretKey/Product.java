package com.example.Restaurant_Management.GenerateSecretKey;

public class Product {

    private int id;
    private String name;
    private double value;
    private int probility;

    public Product() {
    }

    public Product(int id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getProbility() {
        return probility;
    }

    public void setProbility(int probility) {
        this.probility = probility;
    }
}
