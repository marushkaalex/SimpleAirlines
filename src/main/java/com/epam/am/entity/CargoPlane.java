package com.epam.am.entity;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CargoPlane extends Plane {
    private final static Logger LOG = LoggerFactory.getLogger(CargoPlane.class);
    private double maxCargoWeight;
    private List<Cargo> cargo;

    public CargoPlane(CargoPlane.Builder builder) {
        super(builder);
        maxCargoWeight = builder.maxCargoWeight;
        cargo = builder.cargo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public Cargo getCargo(long id) {
        for (Cargo cargo1 : cargo) {
            if (cargo1.getId() == id) {
                return cargo1;
            }
        }
        throw new IllegalArgumentException("No cargo with id = " + id);
    }

    public List<Cargo> getAllCargo() {
        return cargo;
    }

    public boolean addCargo(Cargo val) {
        if (getCargoWeight() < maxCargoWeight) {
            cargo.add(val);
            LOG.info("{}: cargo {} has been added", getBriefInfo(), val);
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

    public int getCargoCount() {
        return cargo.size();
    }

    @Override
    public String toString() {
        return super.toString() + "CargoPlane{" +
                "maxCargoWeight=" + maxCargoWeight +
                ", cargo=" + cargo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CargoPlane that = (CargoPlane) o;

        return Double.compare(that.maxCargoWeight, maxCargoWeight) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(maxCargoWeight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class Builder extends Plane.Builder<Builder> {
        private double maxCargoWeight = 0;
        private List<Cargo> cargo;

        public Builder maxCargoWeight(double val) {
            maxCargoWeight = numberCheck(val);
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

    /**
     * Compares by carrying capacity.
     */
    public static class Comparator implements java.util.Comparator<CargoPlane> {
        @Override
        public int compare(CargoPlane o1, CargoPlane o2) {
            if (o1.getMaxCargoWeight() == o2.getMaxCargoWeight()) {
                return 0;
            }
            return o1.getMaxCargoWeight() > o2.getMaxCargoWeight() ? 1 : -1;
        }
    }
}
