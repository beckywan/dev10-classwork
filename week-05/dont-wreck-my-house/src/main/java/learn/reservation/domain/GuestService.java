package learn.reservation.domain;

import learn.reservation.data.GuestRepository;

public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }
}
