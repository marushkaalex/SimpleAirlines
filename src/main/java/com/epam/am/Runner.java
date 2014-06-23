package com.epam.am;

import com.epam.am.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

public class Runner {

    private final static Logger LOG = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        Airline airline = new Airline(0, "Ololo airlines");
        airline.addRandomPlanes(10);
        AirlineLogic.sortPlanesByType(airline);
        LOG.info("{}", AirlineLogic.getPlanesInfo(airline));

        airline.addPlane(PlaneFactory.createRandomPlane(11));
        airline.removePlane(3);

        Plane plane = airline.getPlaneById(0);
        AirlineLogic.addRandomCargoOrPassenger(plane);
        plane.flyTo(new Point(500, 500));

        List<Airliner> airliners = AirlineLogic.getAirliners(airline);
        List<Airliner> foundAirliners = AirlineLogic.findAirlinersBySeatingCapacity(airliners, 100, 200);
        AirlineLogic.sortAirliners(foundAirliners);
        LOG.info("{}", AirlineLogic.getPlanesInfo(foundAirliners));

        List<CargoPlane> cargoPlanes = AirlineLogic.getCargoPlanes(airline);
        List<CargoPlane> foundCargoPlanes = AirlineLogic.findCargoPlanesByCarryingCapacity(cargoPlanes, 400, 800);
        AirlineLogic.sortCargoPlanes(foundCargoPlanes);
        LOG.info("{}", AirlineLogic.getPlanesInfo(foundCargoPlanes));

        String model = RandomStringGetter.getString(RandomStringGetter.PLANE_MODEL);
        LOG.info("You search {} model. Found:", model);
        List<Plane> filteredPlanes = (AirlineLogic.findByFilter(airline, (plane1) -> plane1.getModel().equals(model)));
        LOG.info("{}", AirlineLogic.getPlanesInfo(filteredPlanes));
    }
}
