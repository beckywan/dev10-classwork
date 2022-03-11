package learn.reservation.data;

import learn.reservation.models.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository{

    public final static Guest GUEST = makeGuest();

    private final ArrayList<Guest> guests = new ArrayList<>();

    public GuestRepositoryDouble() {guests.add(GUEST); }

    private static Guest makeGuest() {
        Guest guest = new Guest();
        guest.setId(1);
        guest.setFirstName("Test");
        guest.setLastName("Tester");
        guest.setPhone("(800) 000-0000");
        guest.setEmail("test@test.com");
        guest.setState("NY");
        return guest;
    }

    @Override
    public List<Guest> findAll() {
        return guests;
    }

    public Guest findByEmail(String email) {
        return guests.stream()
                .filter(i -> i.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
