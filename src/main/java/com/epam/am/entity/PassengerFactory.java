package com.epam.am.entity;

import static com.epam.am.entity.RandomStringGetter.*;

public class PassengerFactory {

    private PassengerFactory() {
    }

    public static Passenger createRandomPassenger(long id) {
        return new Passenger(id, getString(PASSENGER_NAME), getString(PASSENGER_SURNAME));
    }
}
