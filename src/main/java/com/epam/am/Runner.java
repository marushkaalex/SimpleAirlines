package com.epam.am;

//todo factories DONE
//todo flyTo() DONE
//todo clone() DONE
//todo compare DONE
//todo logs
//todo sort DONE
//todo equals DONE
//todo deepClone() DONE
//todo use Comparator DONE
//todo find in range DONE

import com.epam.am.entity.Airline;
import com.epam.am.entity.AirlineLogic;
import com.epam.am.entity.CargoPlane;

import java.util.List;

public class Runner {
    public static void main(String[] args) throws CloneNotSupportedException {
        Airline a = new Airline(0, "Ololo airlines");
        a.addRandomPlanes(10);
        System.out.println(AirlineLogic.getPlanesInfo(a));
        List<CargoPlane> l = AirlineLogic.getCargoPlanes(a);
        System.out.println(AirlineLogic.getPlanesInfo(l));
        List<CargoPlane> f = AirlineLogic.findCargoPlanesByCarryingCapacity(l, 100, 800);
        AirlineLogic.sortCargoPlanes(f);
        System.out.println(AirlineLogic.getPlanesInfo(f));
    }
}
