package learn.reservation.domain;

import learn.reservation.data.DataException;
import learn.reservation.data.GuestRepository;
import learn.reservation.data.HostRepository;
import learn.reservation.data.ReservationRepository;
import learn.reservation.models.Guest;
import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    public ReservationService(ReservationRepository reservationRepository, GuestRepository guestRepository, HostRepository hostRepository) {
        this.reservationRepository = reservationRepository;
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }

    public List<Reservation> findByUuid(String uuid) {

        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));
        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));

        List<Reservation> result = reservationRepository.findByUuid(uuid);
        for (Reservation reservation : result) {
            reservation.setGuest(guestMap.get(reservation.getGuest().getId()));
            reservation.setHost(hostMap.get(reservation.getHost().getId()));
        }

        return result.stream().sorted((o1, o2)->o1.getStartDate().
                        compareTo(o2.getStartDate())).
                collect(Collectors.toList());
    }

    public BigDecimal getValue(Reservation reservation) {
        return reservationRepository.getValue(reservation);
    }

    public Result<Reservation> delete(Reservation reservation) throws DataException {
        Result<Reservation> result = new Result<>();

        if (!reservationRepository.delete(reservation)) {
            String message = String.format(
                    "Reservation ID %s was not found.", reservation.getId());
            result.addErrorMessage(message);
        }

        if (reservation.getStartDate().isBefore(LocalDate.now())
                || reservation.getStartDate().isBefore(LocalDate.now())) {
           result.addErrorMessage("Can not delete past reservation.");
        }

        result.setPayload(reservation);
        return result;
    }

    public Result<Reservation> update(Reservation reservation) throws DataException {

        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        if (!reservationRepository.update(reservation)) {
            String message = String.format(
                    "Reservation ID %s was not found.", reservation.getId());
            result.addErrorMessage(message);
        }

        result.setPayload(reservation);
        return result;
    }
    
    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(reservationRepository.add(reservation));
        return result;
    }


    private Result<Reservation> validate(Reservation reservation) {

        Result<Reservation> result = validateNulls(reservation);
        if (!result.isSuccess()) {
            return result;
        }

        validateFields(reservation, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(reservation, result);

        return result;
    }

    private Result<Reservation> validateNulls(Reservation reservation) {
        Result<Reservation> result = new Result<>();

        if (reservation == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (reservation.getGuest() == null) {
            result.addErrorMessage("Guest is required.");
        }

        if (reservation.getHost() == null) {
            result.addErrorMessage("Host is required.");
        }

        if (reservation.getStartDate() == null || reservation.getEndDate() == null) {
            result.addErrorMessage("Reservation dates are required.");
        }

        return result;
    }

    private void validateFields(Reservation reservation, Result<Reservation> result) {
        if (reservation.getStartDate().isBefore(LocalDate.now())) {
            result.addErrorMessage("Reservation date must be in the future.");
        }

        if (reservation.getEndDate().isBefore(reservation.getStartDate())) {
            result.addErrorMessage("Reservation end date must be after the start date.");
        }

        for (Reservation r : reservationRepository.findByUuid(reservation.getUUID())) {
            LocalDate startExist = r.getStartDate();
            LocalDate endExist = r.getEndDate();

            if ((reservation.getStartDate().isBefore(startExist) && reservation.getEndDate().isAfter(startExist))
                    || (reservation.getStartDate().isAfter(startExist) && reservation.getStartDate().isBefore(endExist))){
                result.addErrorMessage("Reservation dates can not overlap existing reservation dates.");
            } else if (reservation.getStartDate().isEqual(startExist) || reservation.getStartDate().isEqual(endExist) ||
                    reservation.getEndDate().isEqual(startExist) || reservation.getEndDate().isEqual(endExist)){
                result.addErrorMessage("Reservation dates can not overlap existing reservation dates.");
            }
        }
        //return result;
    }

    private void validateChildrenExist(Reservation reservation, Result<Reservation> result) {

        if (guestRepository.findByEmail(reservation.getGuest().getEmail()) == null) {
            result.addErrorMessage("Guest does not exist.");
        }

        if (reservation.getHost().getId() == null || hostRepository.findByEmail(reservation.getHost().getEmail()) == null) {
            result.addErrorMessage("Host does not exist.");
        }
    }
}
