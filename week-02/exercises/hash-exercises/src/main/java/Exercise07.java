import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise07 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        int count = 0;
        for (Vehicle l : vehicleMap.values()) {
            if (l.getColor().equalsIgnoreCase("pink")){
                count++;
            }
        }
        System.out.print(count);
        // 1. How many vehicles are Pink (ignore case)?
        // Expected: 54
    }
}
