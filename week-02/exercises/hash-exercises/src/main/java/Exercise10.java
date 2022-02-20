import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise10 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Replace the vehicle with VIN 2G4WD582061270646 with a new Orange 1994 Chrysler School Bus.
        vehicleMap.get("2G4WD582061270646").setColor("Orange");
        vehicleMap.get("2G4WD582061270646").setYear(1994);
        vehicleMap.get("2G4WD582061270646").setMake("Chrysler");
        vehicleMap.get("2G4WD582061270646").setModel("School Bus");



        // 2. Retrieve the new vehicle from `vehicleMap` and print it to confirm it was updated.
        System.out.println(vehicleMap.get("2G4WD582061270646"));

    }
}
