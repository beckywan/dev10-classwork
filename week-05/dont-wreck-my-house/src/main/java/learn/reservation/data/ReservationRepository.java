package learn.reservation.data;

import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByUuid(String uuid);
    //uses Host UUID

    Boolean update(Reservation reservation) throws DataException;

    Reservation add(Reservation reservation) throws DataException;


}
