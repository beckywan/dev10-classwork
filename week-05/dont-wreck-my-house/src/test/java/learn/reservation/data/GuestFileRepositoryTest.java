package learn.reservation.data;

import learn.reservation.models.Guest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {

    GuestFileRepository repository = new GuestFileRepository("./dont-wreck-my-house-data/guests.csv");

    @Test
    void shouldFindAll() {
        assertEquals(1000, repository.findAll().size());
    }

    //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate

    @Test
    void shouldFindHost() {
        Guest guest = repository.findByEmail("slomas0@mediafire.com");
        assertNotNull(guest);
        assertEquals(1, guest.getId());
        assertEquals("Sullivan", guest.getFirstName());
        assertEquals("Lomas", guest.getLastName());
        assertEquals("(702) 7768761", guest.getPhone());
        assertEquals("NV", guest.getState());;
    }

    @Test
    void shouldNotFindHost() {
        Guest guest = repository.findByEmail("TEST@EMAIL.com");
        assertNull(guest);
    }
}