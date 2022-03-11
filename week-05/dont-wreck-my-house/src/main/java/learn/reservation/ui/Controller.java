package learn.reservation.ui;

import learn.reservation.data.DataException;
import learn.reservation.domain.GuestService;
import learn.reservation.domain.HostService;
import learn.reservation.domain.ReservationService;

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

    private void viewReservations() {
    }

    private void addReservations() {
    }

    private void editReservations() {
    }

    private void deleteReservations() {
    }


}
