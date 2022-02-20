import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise03{


    // 1. Create a method to print all Vehicles in a HashMap<String, Vehicle>.
    // Consider making it `public` so you can use it in other exercises.
    HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

    public static void printAll(HashMap<String, Vehicle> vehicleMap) {
        for (String key : vehicleMap.keySet()) {
            Vehicle v = vehicleMap.get(key);
            System.out.printf("%s%n", v);
        }
    }


    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 2. Print `vehicleMap` using your "print all" method.
        printAll(vehicleMap);
    }
}

