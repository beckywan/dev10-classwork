package learn.reservation.domain;

import learn.reservation.data.HostRepository;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }

}
