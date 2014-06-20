package com.epam.am.entity;

public class Cargo {
    private long id;
    private final double weight;

    public Cargo(long id, double weight) {
        this.id = idCheck(id);
        this.weight = checkValue(weight);
    }

    private long idCheck(long id) {
        return id > -1 ? id : -id;
    }

    private double checkValue(double val) {
        return val > 0 ? val : 0;
    }

    public double getWeight() {
        return weight;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }
}
