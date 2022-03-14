package learn.reservation.domain;

import learn.reservation.data.*;
import learn.reservation.models.Guest;
import learn.reservation.models.Host;
import learn.reservation.models.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(
            new ReservationRepositoryDouble(),
            new GuestRepositoryDouble(),
            new HostRepositoryDouble());

    @Test
    void shouldAdd() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(2);
        reservation.setUUID("test uuid");
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusWeeks(2));
        reservation.setTotal(new BigDecimal(500));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);

        Result<Reservation> result = service.add(reservation);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(2, result.getPayload().getId());
    }
    
    @Test
    void shouldNotAddWhenGuestNotFound() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(2);
        reservation.setUUID("test uuid");
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusWeeks(2));
        reservation.setTotal(new BigDecimal(500));
        reservation.setHost(HostRepositoryDouble.HOST);

        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddWhenHostNotFound() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(2);
        reservation.setUUID("test uuid");
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now().plusWeeks(2));
        reservation.setTotal(new BigDecimal(500));
        reservation.setGuest(GuestRepositoryDouble.GUEST);

        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }
    
    @Test
    void shouldNotAddOverlappingDates() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(2);
        reservation.setUUID("test uuid");
        reservation.setStartDate(LocalDate.of(2022, 6, 26));
        reservation.setEndDate(LocalDate.now().plusDays(2));
        reservation.setTotal(new BigDecimal(500));
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setGuest(GuestRepositoryDouble.GUEST);

        Result<Reservation> result = service.add(reservation);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldUpdate() {


    }

    @Test
    void shouldNotUpdateOverlappingDates() {

    }

    @Test
    void shouldDelete() {

    }

    @Test
    void shouldNotDeleteNonexistentReservation() {

    }
}