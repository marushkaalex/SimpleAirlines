package com.epam.am.entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.epam.am.entity.RandomStringGetter.PLANE_MODEL;
import static com.epam.am.entity.RandomStringGetter.getString;

public class PlaneFactory {

    private static final int MIN_PASSENGERS = 10;
    private static final int MAX_PASSENGERS = 50;
    private static final int POINT_BOUND = 100;
    private static final double MIN_TOTAL_CARGO_WEIGHT = 100.0;
    private static final double MAX_TOTAL_CARGO_WEIGHT = 1000.0;
    private static final double MAX_ONE_CARGO_WEIGHT = 100.0;
    private static final int MAX_CARGO_COUNT = 100;
    private static final Random rnd = new Random();

    private PlaneFactory() {
    }

    public static Plane createRandomPlane(long id) {
        boolean b = rnd.nextBoolean();
        return b ? createRandomAirliner(id) : createRandomCargoPlane(id);
    }

    public static Airliner createRandomAirliner(long id) {
        int seats = rndInBounds(MIN_PASSENGERS, MAX_PASSENGERS);
        return new Airliner.Builder()
                .id(id)
                .model(getString(PLANE_MODEL))
                .currentLocation(new Point(rnd.nextInt(POINT_BOUND), rnd.nextInt(POINT_BOUND)))
                .seatingCapacity(seats)
                .passengers(getRandomPassengersList(seats))
                .build();
    }

    public static CargoPlane createRandomCargoPlane(long id) {
        double weight = rndInBounds(MIN_TOTAL_CARGO_WEIGHT, MAX_TOTAL_CARGO_WEIGHT);
        return new CargoPlane.Builder()
                .id(id)
                .model(getString(PLANE_MODEL))
                .currentLocation(new Point(rnd.nextInt(POINT_BOUND), rnd.nextInt(POINT_BOUND)))
                .maxCargoWeight(weight)
                .cargo(getRandomCargoList(weight))
                .build();
    }

    private static List<Passenger> getRandomPassengersList(int max) {
        List<Passenger> result = new ArrayList<Passenger>();
        int bound = rnd.nextInt(max);
        for (int i = 0; i < bound; i++) {
            result.add(PassengerFactory.createRandomPassenger(i));
        }
        return result;
    }

    private static List<Cargo> getRandomCargoList(double maxWeight) {
        List<Cargo> result = new ArrayList<Cargo>();
        int bound = rnd.nextInt(MAX_CARGO_COUNT);
        for (int i = 0; i < bound; i++) {
            Cargo cargo = new Cargo(i, rndInBounds(0, MAX_ONE_CARGO_WEIGHT));
            if (cargo.getWeight() > maxWeight - calculateCargoWeight(result)) {
                return result;
            }
            result.add(cargo);
        }
        return result;
    }

    private static double calculateCargoWeight(List<Cargo> list) {
        double result = 0;
        for (Cargo cargo : list) {
            result += cargo.getWeight();
        }
        return result;
    }

    private static double rndInBounds(double min, double max) {
        return rnd.nextDouble() * (max - min + 1) + min;
    }

    private static int rndInBounds(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }
}
