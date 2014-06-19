package com.epam.am.entity;

public class Cargo {
    private final double weight;

    public Cargo(double weight) {
        this.weight = checkValue(weight);
    }

    private double checkValue(double val) {
        return val > 0 ? val : 0;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "weight=" + weight +
                '}';
    }
}
