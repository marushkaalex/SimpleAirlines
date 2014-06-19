package com.epam.am.entity;

import java.awt.*;

public abstract class Plane implements Flyable {
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
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
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
