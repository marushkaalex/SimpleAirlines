package com.epam.am.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Airline {
    private long id;
    private String name;
    private List<Plane> planes = new ArrayList<>();

    public Airline(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Airline(long id, String name, List<Plane> planes) {
        this.id = id;
        this.name = name;
        this.planes = planes;
    }

    /**
     * @param plane airliner or cargo plane to be added
     * @return true - if plane has been added <br>false - if there is a plane with the same id in the airline
     */
    public boolean addPlane(Plane plane) {
        for (Plane plane1 : planes) {
            if (plane.getId() == plane1.getId()) {
                return false;
            }
        }
        planes.add(plane);
        return true;
    }

    public void addRandomPlanes(int planesCount) {
        for (int i = 0; i < planesCount; i++) {
            planes.add(PlaneFactory.createRandomPlane(i));
        }
    }

    public void removePlane(Plane plane) {
        planes.remove(plane);
    }

    public void removePlane(long id) {
        for (Plane plane : planes) {
            if (plane.getId() == id) {
                planes.remove(plane);
                break;
            }
        }
    }

    public Plane getPlaneById(long id) {
        for (Plane plane : planes) {
            if (plane.getId() == id) {
                return plane;
            }
        }
        throw new IllegalArgumentException("No airplane with id = " + id);
    }

    public void sortPlanesById() {
        Collections.sort(planes);
    }

    public void sortPlanesByType() {
        Collections.sort(planes, (planeA, planeB) -> {
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

    public List<Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planes=" + planes +
                '}';
    }
}
