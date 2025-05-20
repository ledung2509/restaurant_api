package com.example.Restaurant_Management.GenerateSecretKey;

public class Range {

    private int start;
    private int end;
    private Product product;

    public Range() {
    }

    public Range(int start, int end, Product product) {
        this.start = start;
        this.end = end;
        this.product = product;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
