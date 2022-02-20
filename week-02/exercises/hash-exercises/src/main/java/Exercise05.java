import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise05 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();
        HashMap<String, Vehicle> newVehicleMap = new HashMap<String, Vehicle>();

        Vehicle newCar1 = new Vehicle();
        newCar1.setColor("blue");
        newVehicleMap.put("newCar1", newCar1);

        Vehicle newCar2 = new Vehicle();
        newCar2.setColor("orange");
        newVehicleMap.put("newCar2", newCar2);


        vehicleMap.putAll(newVehicleMap);

        for (String key : vehicleMap.keySet()) {
            Vehicle v = vehicleMap.get(key);
            System.out.printf("%s%n", v);
        }

        // 1. Instantiate a new HashMap<String, Vehicle>.
        // 2. Add two vehicles to the new map.
        // 3. Add items from the new map to `vehicleMap` using the `putAll` method.
        // 4. Confirm the vehicles were added by retrieving on with its VIN and printing it to the console.
    }
}
