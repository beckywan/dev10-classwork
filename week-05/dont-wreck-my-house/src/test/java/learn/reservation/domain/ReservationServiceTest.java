package learn.reservation.domain;

import learn.reservation.data.GuestRepositoryDouble;
import learn.reservation.data.HostRepositoryDouble;
import learn.reservation.data.ReservationRepositoryDouble;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationServiceTest {

    ReservationService service = new ReservationService(
            new ReservationRepositoryDouble(),
            new GuestRepositoryDouble(),
            new HostRepositoryDouble());

    @Test
    void shouldAdd() {
    }
    
    @Test
    void shouldNotAddWhenGuestNotFound()  {

    }

    @Test
    void shouldNotAddWhenHostNotFound()  {
    }
    
    @Test
    void shouldNotAddOverlappingDates() {

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