package com.epam.am.entity;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.List;

public class CargoPlane extends Plane {
    private double maxCargoWeight;
    private List<Cargo> cargo;

    public CargoPlane(CargoPlane.Builder builder) {
        super(builder);
        maxCargoWeight = builder.maxCargoWeight;
        cargo = builder.cargo;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public boolean addCargo(Cargo val) {
        if (getCargoWeight() < maxCargoWeight) {
            cargo.add(val);
            return true;
        }
        return false;
    }

    public double getCargoWeight() {
        double result = 0;
        for (Cargo val : cargo) {
            result += val.getWeight();
        }
        return result;
    }

    @Override
    public void flyTo(Point point) throws Exception {

    }

    @Override
    public String toString() {
        return super.toString() + "CargoPlane{" +
                "maxCargoWeight=" + maxCargoWeight +
                ", cargo=" + cargo +
                '}';
    }

    public static class Builder extends Plane.Builder<Builder> {
        private double maxCargoWeight = 0;
        private List<Cargo> cargo;

        public Builder maxCargoWeight(double val) {
            maxCargoWeight = val;
            return this;
        }

        public Builder cargo(@NotNull List<Cargo> val) {
            if (val == null) throw new IllegalArgumentException("Cargo list must not be null");
            double weight = 0;
            for (Cargo cargo1 : val) {
                weight += cargo1.getWeight();
            }
            if (weight > maxCargoWeight) throw new IllegalArgumentException("Too much cargo");
            cargo = val;
            return this;
        }

        public CargoPlane build() {
            return new CargoPlane(this);
        }
    }
}
