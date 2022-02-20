import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise08 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        HashMap<String, Vehicle> twoThousandSix = new HashMap<>();

        // 1. Instantiate a new HashMap<String, Vehicle> named `twoThousandSix`.
        // 2. Loop through `vehicleMap` and all 2006 vehicles to `twoThousandSix`;
        int count = 0;
        for (Vehicle l : vehicleMap.values()) {
            if (l.getYear() == 2006){
                twoThousandSix.put(String.valueOf(count), l);
                count++;
            }
        }

        // 3. Loop through `twoThousandSix` and display all vehicles.
        // (You may want to use your print all method from Exercise03.)
        for (Vehicle l : twoThousandSix.values()) {
            System.out.printf("%s%n", l);
        }

        // 4. How many 2006 vehicles are there? (Expected: 50)
        System.out.println(twoThousandSix.size());


    }
}
