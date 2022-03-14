package learn.reservation.ui;

import learn.reservation.data.DataException;
import learn.reservation.domain.GuestService;
import learn.reservation.domain.HostService;
import learn.reservation.domain.ReservationService;
import learn.reservation.domain.Result;
import learn.reservation.models.Guest;
import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    private final GuestService guestService;
    private final HostService hostService;
    private final ReservationService reservationService;
    private final View view;


    public Controller(GuestService guestService, HostService hostService, ReservationService reservationService, View view) {
        this.guestService = guestService;
        this.hostService = hostService;
        this.reservationService = reservationService;
        this.view = view;
    }

    public void run() {
        view.displayHeader("Welcome to Travel Reservations!");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MenuOptions option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_GUEST:
                    viewGuests();
                    break;
                case VIEW_HOST:
                    viewHosts();
                    break;
                case VIEW_RESERVATIONS:
                    viewReservations();
                    break;
                case ADD_RESERVATION:
                    addReservations();
                    break;
                case EDIT_RESERVATION:
                    editReservations();
                    break;
                case DELETE_RESERVATION:
                    deleteReservations();
                    break;
            }
        } while (option != MenuOptions.EXIT);
    }

    private void viewHosts() {
        List<Host> hosts = hostService.findAll();
        view.displayAllHost(hosts);
        view.enterToContinue();
    }

    private void viewGuests() {
        List<Guest> guests = guestService.findAll();
        view.displayAllGuest(guests);
        view.enterToContinue();
    }

    private void viewReservations() {
        view.displayHeader(MenuOptions.VIEW_RESERVATIONS.getMessage());
        String email = view.getHostEmail();
        Host host = hostService.findByEmail(email);
        if (host != null) {
            view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
            String uuid = host.getId();
            List<Reservation> reservations = reservationService.findByUuid(uuid);
            view.displayReservations(reservations);
        }
        view.enterToContinue();
    }

    private void addReservations() throws DataException {
        view.displayHeader(MenuOptions.ADD_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        if (guest == null) {
            return;
        }
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        if (host == null) {
            return;
        } else {
            view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
            String uuid = host.getId();
            List<Reservation> reservations = reservationService.findByUuid(uuid);
            view.displayReservations(reservations);
            System.out.printf("%nStandard Rate: $%.2f%nWeekend Rate: $%.2f%n",
                    host.getStandardRate(), host.getWeekendRate());
        }

        Reservation reservation = view.makeReservation(guest, host);
        BigDecimal value = (reservationService.getValue(reservation));
        reservation.setTotal(value);
        System.out.printf("%nTotal: $%.2f%n", reservation.getTotal());
        if (view.getConfirm() == true) {
            Result<Reservation> result = reservationService.add(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s created.", result.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        } else {
            System.out.println("Okay. Returning to main menu.");
        }
    }

    private void editReservations() throws DataException {
        view.displayHeader(MenuOptions.EDIT_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        if (guest == null) {
            return;
        }
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        if (host == null) {
            return;
        }

        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        String uuid = host.getId();
        List<Reservation> reservations = reservationService.findByUuid(uuid);
        List<Reservation> reservationStream =
                reservations.stream()
                        .filter(reservation -> reservation.getGuest().getId() == guest.getId())
                        .collect(Collectors.toList());
        view.displayReservations(reservationStream);
        if (reservations.isEmpty()) {
            return;
        }
        System.out.printf("%nStandard Rate: $%.2f%nWeekend Rate: $%.2f%n",
                host.getStandardRate(), host.getWeekendRate());
        int reservationNumber = view.getReservationID();
        Reservation reservation = reservationStream.stream()
                .filter(i -> Objects.equals(i.getId(), reservationNumber))
                .findFirst()
                .orElse(null);
        if (reservation == null) {
            System.out.printf("Reservation with ID#%s is not found.%n", reservationNumber);
            return;
        }
        view.editReservation(reservation);
        BigDecimal value = (reservationService.getValue(reservation));
        reservation.setTotal(value);
        System.out.printf("%nTotal: $%.2f%n", reservation.getTotal());
        if (view.getConfirm() == true) {
            Result<Reservation> result = reservationService.update(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s is updated.", result.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        } else {
            System.out.println("Okay. Returning to main menu.");
        }

    }

    private void deleteReservations() throws DataException {
        view.displayHeader(MenuOptions.DELETE_RESERVATION.getMessage());
        String guestEmail = view.getGuestEmail();
        Guest guest = guestService.findByEmail(guestEmail);
        if (guest == null) {
            return;
        }
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByEmail(hostEmail);
        if (host == null) {
            return;
        }

        view.displayHeader(host.getLastName() + ": " + host.getCity() + ", " + host.getState());
        String uuid = host.getId();
        List<Reservation> reservations = reservationService.findByUuid(uuid);
        List<Reservation> reservationStream =
                reservations.stream()
                        .filter(reservation -> reservation.getGuest().getId() == guest.getId())
                        .filter(reservation -> reservation.getStartDate().isAfter(LocalDate.now()))
                        .collect(Collectors.toList());
        view.displayReservations(reservationStream);
        if (reservationStream.isEmpty()) {
            return;
        }
        int reservationNumber = view.getReservationID();
        Reservation reservation = reservationStream.stream()
                .filter(i -> Objects.equals(i.getId(), reservationNumber))
                .findFirst()
                .orElse(null);
        if (reservation == null) {
            System.out.printf("Reservation with ID#%s is not found.%n", reservationNumber);
            return;
        }
        if (view.getConfirm() == true) {
            Result<Reservation> result = reservationService.delete(reservation);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else {
                String successMessage = String.format("Reservation %s is deleted.", result.getPayload().getId());
                view.displayStatus(true, successMessage);
            }
        } else {
            System.out.println("Okay. Returning to main menu.");
        }
    }
}
