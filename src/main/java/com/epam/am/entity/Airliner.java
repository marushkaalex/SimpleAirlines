package com.epam.am.entity;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Airliner extends Plane implements Cloneable {
    private final static Logger LOG = LoggerFactory.getLogger(Airliner.class);
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
            for (Passenger passenger1 : passengers) {
                if (passenger.getId() == passenger1.getId()) {
                    return false;
                }
            }
            passengers.add(passenger);
            LOG.info("{}: passenger {} has been added", getBriefInfo(), passenger);
            return true;
        }
        LOG.info("{}: passenger {} hasn't been added", getBriefInfo(), passenger);
        return false;
    }

    public Passenger getPassenger(long id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        throw new IllegalArgumentException("no passenger with id = " + id);
    }

    public Passenger getPassenger(String lastName) {
        for (Passenger passenger : passengers) {
            if (passenger.getLastName().equals(lastName)) {
                return passenger;
            }
        }
        throw new IllegalArgumentException("no passenger with lastname \"" + lastName + "\"");
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        LOG.info("{}: passenger {} has been removed", getBriefInfo(), passenger);
    }

    public void removePassenger(long id) {
        for (Passenger passenger : passengers) {
            if (passenger.getId() == id) {
                passengers.remove(passenger);
                LOG.info("{}: passenger {} has been removed", getBriefInfo(), passenger);
                break;
            }
        }
    }

    public void removePassenger(String lastName) {
        for (Passenger passenger : passengers) {
            if (passenger.getLastName().equals(lastName)) {
                passengers.remove(passenger);
                LOG.info("{}: passenger {} has been removed", getBriefInfo(), passenger);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Airliner{" +
                "seatingCapacity=" + seatingCapacity +
                ", passengers=" + passengers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Airliner airliner = (Airliner) o;

        return seatingCapacity == airliner.seatingCapacity;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + seatingCapacity;
        return result;
    }

    public Airliner deepClone() {
        List<Passenger> pas = new ArrayList<>();
        for (Passenger passenger : passengers) {
            pas.add(passenger);
        }
        return new Builder().id(getId())
                .model(getModel())
                .currentLocation(new Point(getCurrentLocation().x, getCurrentLocation().y))
                .seatingCapacity(seatingCapacity)
                .passengers(pas)
                .build();
    }

    public static class Builder extends Plane.Builder<Builder> {
        private int seatingCapacity = 0;
        private List<Passenger> passengers;

        public Builder seatingCapacity(int val) {
            seatingCapacity = numberCheck(val);
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

    /**
     * Compares by
     */
    public static class Comparator implements java.util.Comparator<Airliner> {
        @Override
        public int compare(Airliner o1, Airliner o2) {
            if (o1.getSeatingCapacity() == o2.getSeatingCapacity()) {
                return 0;
            }
            return o1.getSeatingCapacity() > o2.getSeatingCapacity() ? 1 : -1;
        }
    }
}
