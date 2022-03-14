package learn.reservation.data;

import learn.reservation.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepositoryDouble implements ReservationRepository{
    final LocalDate date = LocalDate.of(2022, 6, 26);

    private final ArrayList<Reservation> reservations = new ArrayList<>();

    public ReservationRepositoryDouble() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setUUID("498604db-b6d6-4599-a503-3d8190fda823");
        reservation.setStartDate(date);
        reservation.setEndDate(date.plusWeeks(1));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);
        reservation.setTotal(BigDecimal.valueOf(200));
        reservations.add(reservation);
    }

    @Override
    public List<Reservation> findByUuid(String uuid) {
        return reservations.stream()
                .filter(i -> i.getUUID().equals(uuid))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation add(Reservation reservation) throws DataException {
        reservation.setUUID("498604db-b6d6-4599-a503-3d8190fda823");
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataException {
        return reservation.getId() == 1;

    }

    @Override
    public BigDecimal getValue(Reservation reservation) {
        return null;
    }

    @Override
    public boolean update(Reservation reservation) throws DataException {
        return reservation.getId() == 1;
    }
}
