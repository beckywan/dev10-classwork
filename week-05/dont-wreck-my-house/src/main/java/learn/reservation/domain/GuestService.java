package learn.reservation.domain;

import learn.reservation.data.GuestRepository;
import learn.reservation.models.Guest;
import learn.reservation.models.Host;

import java.util.List;

public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findAll() {
        return repository.findAll();
    }

    public Guest findByEmail(String email) {
        Guest guest = repository.findByEmail(email);
        if (guest == null) {
            System.out.println("No guest found.");
        }
        return guest;
    }
}

