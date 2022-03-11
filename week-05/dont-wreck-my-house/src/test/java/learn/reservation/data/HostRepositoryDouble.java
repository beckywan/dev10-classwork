package learn.reservation.data;

import learn.reservation.models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostRepositoryDouble implements HostRepository {
    public final static Host HOST = makeHost();

    private final ArrayList<Host> hosts = new ArrayList<>();

    public HostRepositoryDouble() {hosts.add(HOST); }

    private static Host makeHost() {
        Host host = new Host();
        host.setId("0e4707f4-407e-4ec9-9665-baca0aabe88c");
        host.setWeekendRate(new BigDecimal("300"));
        host.setStandardRate(new BigDecimal("200"));
        host.setAddress("100 ST 101 RD");
        host.setPostalCode(10010);
        host.setCity("NY");
        host.setState("NY");
        host.setLastName("Host");
        host.setPhone("(999) 000-0000");
        host.setEmail("host@test.com");
        return host;
    }

    @Override
    public List<Host> findAll() {
        return hosts;
    }

    public Host findByEmail(String email) {
        return hosts.stream()
                .filter(i -> i.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
