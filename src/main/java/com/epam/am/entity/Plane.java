package com.epam.am.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public abstract class Plane implements Flyable, Comparable<Plane>, Cloneable {
    private final static Logger LOG = LoggerFactory.getLogger(Plane.class);
    private long id;
    private String model;
    private Point currentLocation;

    protected Plane(Builder builder) {
        id = builder.id;
        model = builder.model;
        currentLocation = builder.currentLocation;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getBriefInfo() {
        return this.getClass().getSimpleName() + ", id=" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        return model.equals(plane.model);
    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }

    @Override
    public void flyTo(Point point) {
        LOG.info(getBriefInfo() + "has flown from " + currentLocation);
        currentLocation = (Point) point.clone();
        LOG.info("to " + currentLocation);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
    }


    @Override
    public int compareTo(Plane o) {
        if (id == o.getId()) {
            return 0;
        }
        return id > o.getId() ? 1 : -1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected static abstract class Builder<T extends Builder> {
        private long id = 0;
        private String model = "<<no info>>";
        private Point currentLocation;

        public Builder() {
        }

        public T id(long val) {
            id = numberCheck(val);
            return (T) this;
        }

        public T model(String val) {
            model = val;
            return (T) this;
        }

        public T currentLocation(Point val) {
            currentLocation = val;
            return (T) this;
        }

        protected int numberCheck(int n) {
            if (n < -1) {
                throw new IllegalArgumentException("Argument must be >= 0");
            }
            return n;
        }

        protected long numberCheck(long n) {
            if (n < -1) {
                throw new IllegalArgumentException("Argument must be >= 0");
            }
            return n;
        }

        protected double numberCheck(double n) {
            if (n < 0) {
                throw new IllegalArgumentException("Argument must be > 0");
            }
            return n;
        }
    }
}
