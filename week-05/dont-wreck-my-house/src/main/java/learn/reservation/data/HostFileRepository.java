package learn.reservation.data;

import learn.reservation.models.Host;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HostFileRepository implements HostRepository {

    private static final String HEADER = "id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";
    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // read header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Host host = deserialize(line);
                if (host != null) {
                    result.add(host);
                }
            }
        } catch (IOException ex) {
           //
        }
        return result;
    }

    @Override
    public Host findByEmail(String email) {
        return findAll().stream()
                .filter(i -> Objects.equals(i.getEmail(), email))
                .findFirst()
                .orElse(null);
    }

    //id,last_name,email,phone,address,city,
    //state,postal_code,standard_rate,weekend_rate
    private Host deserialize(String line) {
        String[] fields = line.split(",", -1);
        if (fields.length == 10) {
            Host result = new Host();
            result.setId(fields[0]);
            result.setLastName(fields[1]);
            result.setEmail(fields[2]);
            result.setPhone(fields[3]);
            result.setAddress(fields[4]);
            result.setCity(fields[5]);
            result.setState(fields[6]);
            result.setPostalCode(Integer.parseInt(fields[7]));
            result.setStandardRate(new BigDecimal(fields[8]));
            result.setWeekendRate(new BigDecimal(fields[9]));
            return result;
        }
        return null;
    }
}
