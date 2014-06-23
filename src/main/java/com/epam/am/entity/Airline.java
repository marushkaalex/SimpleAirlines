package com.epam.am.entity;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Airline {
    private final static Logger LOG = LoggerFactory.getLogger(Airline.class);
    private long id;
    private String name;
    private List<Plane> planes = new ArrayList<>();

    public Airline(long id, String name) {
        this.id = idCheck(id);
        this.name = name;
        LOG.info("Airline has been created: {}", this);
    }

    public Airline(long id, String name, @NotNull List<Plane> planes) {
        this.id = idCheck(id);
        this.name = name;
        this.planes = planes;
        LOG.info("Airline has been created: {}", this);
    }

    private long idCheck(long id) {
        return id > -1 ? id : -id;
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
        LOG.info(plane.getBriefInfo() + "{} has been added into \"{}\"", plane.getBriefInfo(), name);
        return true;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRandomPlanes(int planesCount) {
        for (int i = 0; i < planesCount; i++) {
            addPlane(PlaneFactory.createRandomPlane(i));
        }
    }

    public void removePlane(Plane plane) {
        planes.remove(plane);
        LOG.info(plane.getBriefInfo() + "{} has been removed from \"{}\"", plane.getBriefInfo(), name);
    }

    public void removePlane(long id) {
        for (Plane plane : planes) {
            if (plane.getId() == id) {
                planes.remove(plane);
                LOG.info(plane.getBriefInfo() + "{} has been removed from \"{}\"", plane.getBriefInfo(), name);
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
