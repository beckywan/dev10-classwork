package learn.reservation.data;

import learn.reservation.models.Guest;
import learn.reservation.models.Guest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuestFileRepository implements GuestRepository{

    private static final String HEADER = "guest_id,first_name,last_name,email,phone,state";
    private final String filePath;

    public GuestFileRepository(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public List<Guest> findAll() {
        ArrayList<Guest> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // read header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Guest guest = deserialize(line);
                if (guest != null) {
                    result.add(guest);
                }
            }
        } catch (IOException ex) {
            //
        }
        return result;
    }

    @Override
    public Guest findByEmail(String email) {
        return findAll().stream()
                .filter(i -> Objects.equals(i.getEmail(), email))
                .findFirst()
                .orElse(null);
    }
    

    //guest_id,first_name,last_name,email,phone,state
    private Guest deserialize(String line) {
        String[] fields = line.split(",", -1);
        if (fields.length == 6) {
            Guest result = new Guest();
            result.setId(Integer.parseInt(fields[0]));
            result.setFirstName(fields[1]);
            result.setLastName(fields[2]);
            result.setEmail(fields[3]);
            result.setPhone(fields[4]);
            result.setState(fields[5]);
            return result;
        }
        return null;
    }
}
