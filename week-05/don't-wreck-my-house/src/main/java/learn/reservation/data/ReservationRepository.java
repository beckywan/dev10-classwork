package learn.reservation.data;

import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAll();

    List<Reservation> findById(String id);
    //in data it's through host id;  user uses email

    Boolean update(Reservation reservation) throws DataException;

    Reservation add(Reservation reservation) throws DataException;


}
