package com.epam.am.entity;

import java.awt.*;

public abstract class Plane implements Flyable, Comparable<Plane>, Cloneable {
    private long id;
    private String model;
    private Point currentLocation;

    protected Plane(Builder builder) {
        id = idCheck(builder.id);
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

    private long idCheck(long id) {
        return id > -1 ? id : -id;
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
        currentLocation = (Point) point.clone();
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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected static abstract class Builder<T extends Builder> {
        private long id = 0;
        private String model = "<<no info>>";
        private Point currentLocation;

        public Builder() {
        }

        public T id(long val) {
            id = val;
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
    }
}
