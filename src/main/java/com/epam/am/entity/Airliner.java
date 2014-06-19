package com.epam.am.entity;

import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.util.List;

public class Airliner extends Plane {
    private int seatingCapacity;
    private List<Passenger> passengers;

    public Airliner(Airliner.Builder builder) {
        super(builder);
        seatingCapacity = builder.seatingCapacity;
        passengers = builder.passengers;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() < seatingCapacity) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    @Override
    public void flyTo(Point point) throws Exception {

    }

    @Override
    public String toString() {
        return super.toString() + "Airliner{" +
                "seatingCapacity=" + seatingCapacity +
                ", passengers=" + passengers +
                '}';
    }

    public static class Builder extends Plane.Builder<Builder> {
        private int seatingCapacity = 0;
        private List<Passenger> passengers;

        public Builder seatingCapacity(int val) {
            seatingCapacity = val;
            return this;
        }

        public Builder passengers(@NotNull List<Passenger> val) {
            if (val == null) throw new IllegalArgumentException("Passengers list must not be null");
            if (val.size() > seatingCapacity) throw new IllegalArgumentException("Too much passengers");
            passengers = val;
            return this;
        }

        public Airliner build() {
            return new Airliner(this);
        }
    }
}
