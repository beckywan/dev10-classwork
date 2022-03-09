package learn.reservation.data;

import learn.reservation.models.Guest;
import learn.reservation.models.Host;
import learn.reservation.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFileRepositoryTest {

    static final String SEED_FILE_PATH = "./dont-wreck-my-house-data/reservation-seed.csv";
    static final String TEST_FILE_PATH = "./dont-wreck-my-house-data/reservation-test/3edda6bc-ab95-49a8-8962-d50b53f84b15.csv";
    static final String TEST_DIR_PATH = "./dont-wreck-my-house-data/reservation-test";
    static final int RESERVATION_COUNT = 3;
    static final int NEXT_ID = 4;
    static final int GUEST_ID = 1;

    final String id = "3edda6bc-ab95-49a8-8962-d50b53f84b15";

    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    void shouldFindById() {
        List<Reservation> reservations = repository.findByUuid(id);
        assertEquals(RESERVATION_COUNT, reservations.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(NEXT_ID);
        reservation.setUUID(id);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusWeeks(2));
        reservation.setTotal(new BigDecimal(500));

        //id,start_date,end_date,guest_id,total

        Host host = new Host();
        host.setId(id);
        reservation.setHost(host);

        Guest guest = new Guest();
        guest.setId(GUEST_ID);
        reservation.setGuest(guest);

        reservation = repository.add(reservation);

        assertEquals(4, reservation.getId());

    }

    @Test
    void shouldAddCheck() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(NEXT_ID);
        reservation.setUUID(id);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusWeeks(2));
        reservation.setTotal(new BigDecimal(500));

        //id,start_date,end_date,guest_id,total

        Host host = new Host();
        host.setId(id);
        reservation.setHost(host);

        Guest guest = new Guest();
        guest.setId(GUEST_ID);
        reservation.setGuest(guest);

        reservation = repository.add(reservation);

        List<Reservation> reservations = repository.findByUuid(id);
        assertEquals(RESERVATION_COUNT + 1, reservations.size());

    }


    @Test
    void shouldUpdate() throws DataException {
        Reservation reservation = repository.findByUuid(id).get(2);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusWeeks(2));
        reservation.setTotal(new BigDecimal(500));

        Host host = new Host();
        host.setId(id);
        reservation.setHost(host);

        Guest guest = new Guest();
        guest.setId(GUEST_ID);
        reservation.setGuest(guest);

        assertTrue(repository.update(reservation));

        reservation = repository.findByUuid(id).get(2);
        assertNotNull(reservation);
        assertEquals(LocalDate.now(), reservation.getStartDate());
        assertEquals(LocalDate.now().plusWeeks(2), reservation.getEndDate());
        assertEquals(new BigDecimal(500), reservation.getTotal());
        assertEquals(GUEST_ID, reservation.getGuest().getId());
        assertEquals(id, reservation.getUUID());
    }

    @Test
    void shouldBeFalseForMissingUpdate() throws DataException {
        Reservation doesNotExist = new Reservation();
        doesNotExist.setId(1000);
        assertFalse(repository.update(doesNotExist));

    }

}