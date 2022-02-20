import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;
import java.util.Map.Entry;

public class Exercise06 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Loop over each vehicle in `vehicleMap` and print vehicles with a Dodge make.
        System.out.println("Loop over values:");
        System.out.println("=====================");
        for (Vehicle l : vehicleMap.values()) {
            System.out.printf("%s%n", l);
        }

        System.out.println("Loop over keySet:");
        System.out.println("=====================");
        for (String key : vehicleMap.keySet()) {
            Vehicle l = vehicleMap.get(key);
            System.out.printf("%s%n", l);
        }

        System.out.println("Loop over entrySet:");
        System.out.println("=====================");
        for (Entry<String, Vehicle> entry : vehicleMap.entrySet()) {
            System.out.printf("Key: %s, Value: %s%n",
                    entry.getKey(),
                    entry.getValue());
        }
        
        
        // 2. Loop three times with three different techniques: .values(), .entrySet(), and .keySet().
    }
}
