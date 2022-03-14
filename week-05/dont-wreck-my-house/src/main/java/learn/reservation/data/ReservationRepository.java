package learn.reservation.data;

import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.math.BigDecimal;
import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByUuid(String uuid);
    //uses Host UUID

    boolean update(Reservation reservation) throws DataException;

    Reservation add(Reservation reservation) throws DataException;

    boolean delete(Reservation reservation) throws DataException;

    BigDecimal getValue(Reservation reservation);

}
