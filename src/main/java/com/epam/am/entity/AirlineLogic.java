package com.epam.am.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AirlineLogic {

    private AirlineLogic() {
    }

    public static void sortPlanesById(Airline a) {
        Collections.sort(a.getPlanes());
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
    }

    public static List<Plane> getCargoPlanesAsPlanes(Airline a) {
        List<Plane> result = new ArrayList<>();
        for (Plane plane : a.getPlanes()) {
            if (plane.getClass() == CargoPlane.class) {
                result.add(plane);
            }
        }
        return result;
    }

    public static List<CargoPlane> getCargoPlanes(Airline a) {
        List<CargoPlane> result = new ArrayList<>();
        for (Plane plane : a.getPlanes()) {
            if (plane.getClass() == CargoPlane.class) {
                result.add((CargoPlane) plane);
            }
        }
        return result;
    }

    public static List<Plane> getAirlinersAsPlanes(Airline a) {
        List<Plane> result = new ArrayList<>();
        for (Plane plane : a.getPlanes()) {
            if (plane.getClass() == Airliner.class) {
                result.add(plane);
            }
        }
        return result;
    }

    public static List<Airliner> getAirliners(Airline a) {
        List<Airliner> result = new ArrayList<>();
        for (Plane plane : a.getPlanes()) {
            if (plane.getClass() == Airliner.class) {
                result.add((Airliner) plane);
            }
        }
        return result;
    }

    /**
     * Gives info about plane type, id and model for each plane of the airline
     *
     * @param a airline
     * @return info string
     */
    public static String getPlanesInfo(Airline a) {
        StringBuilder sb = new StringBuilder();
        for (Plane plane : a.getPlanes()) {
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

    /**
     * Sorts cargo planes by carrying capacity
     *
     * @param list list of cargo planes
     */
    public static void sortCargoPlanes(List<CargoPlane> list) {
        Collections.sort(list, new CargoPlane.Comparator());
    }

    /**
     * Sorts airliners by seating capacity
     *
     * @param list list of airliners
     */
    public static void sortAirliners(List<Airliner> list) {
        Collections.sort(list, new Airliner.Comparator());
    }

    public static List<CargoPlane> findCargoPlanesByCarryingCapacity(List<CargoPlane> list, double min, double max) {
        List<CargoPlane> result = new ArrayList<>();
        for (CargoPlane cargoPlane : list) {
            if (cargoPlane.getMaxCargoWeight() > min && cargoPlane.getMaxCargoWeight() < max) {
                result.add(cargoPlane);
            }
        }
        return result;
    }

    public static List<Airliner> findAirlinerBySeatingCapacity(List<Airliner> list, int min, int max) {
        List<Airliner> result = new ArrayList<>();
        for (Airliner airliner : list) {
            if (airliner.getSeatingCapacity() > min && airliner.getSeatingCapacity() < max) {
                result.add(airliner);
            }
        }
        return result;
    }

    public static String getPlanesInfo(List<? extends Plane> a) {
        StringBuilder sb = new StringBuilder();
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
}
