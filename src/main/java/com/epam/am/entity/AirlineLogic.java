package com.epam.am.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AirlineLogic {
    private final static Logger LOG = LoggerFactory.getLogger(AirlineLogic.class);

    private AirlineLogic() {
    }

    public static void sortPlanesById(Airline a) {
        Collections.sort(a.getPlanes());
        LOG.info("Planes have been sorted by id: {}", AirlineLogic.getPlanesInfo(a));
    }

    public static void sortPlanesByType(Airline a) {
        Collections.sort(a.getPlanes(), (planeA, planeB) -> {
            if (planeA.getClass() == planeB.getClass()) {
                return 0;
            }
            if ((planeA.getClass() == Airliner.class) && (planeB.getClass() == CargoPlane.class)) {
                return -1;
            } else {
                return 1;
            }
        });
        LOG.info("Planes have been sorted by type: {}", AirlineLogic.getPlanesInfo(a));
    }

    private static <T extends Plane> List<T> getPlanesList(List<? extends Plane> list, Class<T> clazz, SearchFilter filter) {
        List<T> result = new ArrayList<>();
        for (Plane plane : list) {
            if (filter.search(plane)) {
                result.add((T) plane);
            }
        }
        return result;
    }

    public static List<Plane> getCargoPlanesAsPlanes(Airline a) {
        return getPlanesList(a.getPlanes(), Plane.class, plane -> plane.getClass() == CargoPlane.class);
    }

    public static List<CargoPlane> getCargoPlanes(Airline a) {
        return getPlanesList(a.getPlanes(), CargoPlane.class, plane -> plane.getClass() == CargoPlane.class);
    }

    public static List<Plane> getAirlinersAsPlanes(Airline a) {
        return getPlanesList(a.getPlanes(), Plane.class, plane -> plane.getClass() == Airliner.class);
    }

    public static List<Airliner> getAirliners(Airline a) {
        return getPlanesList(a.getPlanes(), Airliner.class, plane -> plane.getClass() == Airliner.class);
    }

    /**
     * Gives info about plane type, id and model for each plane of the airline
     *
     * @param a airline
     * @return info string
     */
    public static String getPlanesInfo(Airline a) {
        StringBuilder sb = new StringBuilder();
        sb.append("Airline:");
        for (Plane plane : a.getPlanes()) {
            if (plane.getClass() == Airliner.class) {
                sb.append("\nAirliner id=" + plane.getId() + " " + plane.getModel() +
                        ", seating capacity = " + ((Airliner) plane).getSeatingCapacity());
            } else if (plane.getClass() == CargoPlane.class) {
                sb.append("\nCargoPlane id=" + plane.getId() + " " + plane.getModel() +
                        ", carrying capacity = " + ((CargoPlane) plane).getMaxCargoWeight());
            }
        }
        return sb.toString();
    }

    /**
     * Sorts cargo planes by carrying capacity
     *
     * @param list list of cargo planes
     */
    public static void sortCargoPlanes(List<CargoPlane> list) {
        Collections.sort(list, new CargoPlane.Comparator());
        LOG.info("Cargo planes have been sorted by carrying capacity");
    }

    /**
     * Sorts airliners by seating capacity
     *
     * @param list list of airliners
     */
    public static void sortAirliners(List<Airliner> list) {
        Collections.sort(list, new Airliner.Comparator());
        LOG.info("Airliners have been sorted by carrying capacity");
    }

    public static List<CargoPlane> findCargoPlanesByCarryingCapacity(List<CargoPlane> list, double min, double max) {
        List<CargoPlane> result = getPlanesList(list, CargoPlane.class, plane ->
                ((CargoPlane) plane).getMaxCargoWeight() > min
                        && ((CargoPlane) plane).getMaxCargoWeight() < max);
        LOG.info("Request: cargo planes, carrying capacity: min={}, max={}", min, max);
        LOG.info("{} cargo planes have been found:", result.size());
        for (CargoPlane cargoPlane : result) {
            LOG.info("{}, carrying capacity={}", cargoPlane.getBriefInfo(), cargoPlane.getMaxCargoWeight());
        }
        return result;
    }

    public static List<Airliner> findAirlinersBySeatingCapacity(List<Airliner> list, int min, int max) {
        List<Airliner> result = getPlanesList(list, Airliner.class, plane ->
                ((Airliner) plane).getSeatingCapacity() > min
                        && ((Airliner) plane).getSeatingCapacity() < max);
        LOG.info("Request: airliners, seating capacity: min={}, max={}", min, max);
        LOG.info("{} airliners have been found:", result.size());
        for (Airliner airliner : result) {
            LOG.info("{}, seating capacity={}", airliner.getBriefInfo(), airliner.getSeatingCapacity());
        }
        return result;
    }

    public static List<Plane> findByFilter(Airline airline, SearchFilter filter) {
        return getPlanesList(airline.getPlanes(), Plane.class, filter);
    }

    public static List<Plane> findByFilter(List<Plane> list, SearchFilter filter) {
        return getPlanesList(list, Plane.class, filter);
    }

    public static String getPlanesInfo(List<? extends Plane> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("Planes:");
        for (Plane plane : a) {
            if (plane.getClass() == Airliner.class) {
                sb.append("\nAirliner " + plane.getId() + " " + plane.getModel() +
                        ", seating capacity = " + ((Airliner) plane).getSeatingCapacity());
            } else if (plane.getClass() == CargoPlane.class) {
                sb.append("\nCargoPlane " + plane.getId() + " " + plane.getModel() +
                        ", carrying capacity = " + ((CargoPlane) plane).getMaxCargoWeight());
            }
        }
        return sb.toString();
    }

    public static void addRandomCargoOrPassenger(Plane plane) {
        if (plane.getClass() == Airliner.class) {
            ((Airliner) plane).addPassenger(new Passenger(903, "Another", "Passenger"));
        } else {
            ((CargoPlane) plane).addCargo(new Cargo(1234, 45));
        }
    }
}
