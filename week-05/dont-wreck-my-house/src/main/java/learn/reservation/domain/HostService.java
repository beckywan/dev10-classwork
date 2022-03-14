package learn.reservation.domain;

import learn.reservation.data.HostRepository;
import learn.reservation.models.Host;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Objects;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public List<Host> findAll(){
        return repository.findAll();
    }

    public Host findByEmail(String email) {
        Host host = repository.findByEmail(email);
        if (host == null) {
            System.out.println("No host found.");
        }
        return host;
    }
}
