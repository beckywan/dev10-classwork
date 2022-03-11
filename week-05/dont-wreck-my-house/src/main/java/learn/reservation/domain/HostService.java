package learn.reservation.domain;

import learn.reservation.data.HostRepository;
import learn.reservation.models.Host;

import java.util.List;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

    public List<Host> findAll(){
        return repository.findAll();
    }

    public Host findByEmail(String email) {
        return repository.findByEmail(email);
    }



}
