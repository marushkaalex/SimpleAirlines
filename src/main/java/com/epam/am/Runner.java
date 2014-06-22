package com.epam.am;

import com.epam.am.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

public class Runner {

    private final static Logger LOG = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Airline a = new Airline(0, "Ololo airlines");
        a.addRandomPlanes(10);
        AirlineLogic.sortPlanesByType(a);
        LOG.info(AirlineLogic.getPlanesInfo(a));

        a.addPlane(PlaneFactory.createRandomPlane(11));
        a.removePlane(5);

        Plane plane = a.getPlaneById(0);
        if (plane.getClass() == Airliner.class) {
            ((Airliner) plane).addPassenger(new Passenger(903, "Another", "Passenger"));
        } else {
            ((CargoPlane) plane).addCargo(new Cargo(1234, 45));
        }
        plane.flyTo(new Point(500, 500));

        List<Airliner> airliners = AirlineLogic.getAirliners(a);
        List<Airliner> foundAirliners = AirlineLogic.findAirlinersBySeatingCapacity(airliners, 100, 200);
        AirlineLogic.sortAirliners(foundAirliners);
        LOG.info(AirlineLogic.getPlanesInfo(foundAirliners));

        List<CargoPlane> cargoPlanes = AirlineLogic.getCargoPlanes(a);
        List<CargoPlane> foundCargoPlanes = AirlineLogic.findCargoPlanesByCarryingCapacity(cargoPlanes, 400, 800);
        AirlineLogic.sortCargoPlanes(foundCargoPlanes);
        LOG.info(AirlineLogic.getPlanesInfo(foundCargoPlanes));
    }
}
