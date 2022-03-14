package learn.reservation.ui;

import learn.reservation.data.ReservationRepository;
import learn.reservation.domain.ReservationService;
import learn.reservation.models.Guest;
import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class View {
    private final ConsoleIo io;

    public View(ConsoleIo io) {
        this.io = io;
    }

    public MenuOptions selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MenuOptions option : MenuOptions.values()) {
            io.printf("%s. %s%n", option.getValue(), option.getMessage());
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max - 1);
        return MenuOptions.fromValue(io.readInt(message, min, max));
    }

    public Reservation makeReservation(Guest guest, Host host) {
        Reservation reservation = new Reservation();
        reservation.setHost(host);
        reservation.setGuest(guest);
        reservation.setUUID(host.getId());
        reservation.setStartDate(io.readLocalDate("Start Date [MM/dd/yyyy]: "));
        reservation.setEndDate(io.readLocalDate("End Date [MM/dd/yyyy]: "));
        return reservation;
    }

    public Reservation editReservation(Reservation reservation) {
        LocalDate start = io.readDate("Start Date (" + reservation.getStartDate() + "): ");
        LocalDate end = io.readDate("End Date (" + reservation.getEndDate() + "): ");
        if (start != null) {
            reservation.setStartDate(start);
        }
        if (end != null) {
            reservation.setEndDate(end);
        }
        return reservation;
    }

    public boolean getConfirm() {
        return io.readBoolean("Confirm action? [y/n]: ");
    }

    public int getReservationID() {
        return io.readInt("Enter Chosen Reservation ID Number: ");
    }

    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public String getHostEmail() {
        return io.readRequiredString("Host Email: ");
    }

    public String getGuestEmail() {
        return io.readRequiredString("Guest Email: ");
    }

    public void displayReservations(List<Reservation> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found.");
        }

        for (Reservation r : reservations) {
            io.printf("ID: %5s || Dates: %s to %s || Guest: %s %s || Email: %s || Total: $%.2f%n",
                    r.getId(),
                    r.getStartDate(),
                    r.getEndDate(),
                    r.getGuest().getFirstName(),
                    r.getGuest().getLastName(),
                    r.getGuest().getEmail(),
                    r.getTotal());
        }
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }

    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayAllHost(List<Host> hosts) {
        if (hosts == null || hosts.isEmpty()) {
            io.println("No reservations found.");
        }

        for (Host h : hosts) {
            io.printf("Last Name: %s || Email: %s ||" +
                            " Address: %s, %s, %s, %s || Standard Rate: %.2f ||" +
                            " Weekend Rate: %.2f || Phone: %s%n",
                    h.getLastName(),
                    h.getEmail(),
                    h.getAddress(),
                    h.getCity(),
                    h.getState(),
                    h.getPostalCode(),
                    h.getStandardRate(),
                    h.getWeekendRate(),
                    h.getPhone());
        }

    }

    public void displayAllGuest(List<Guest> guests) {
        if (guests == null || guests.isEmpty()) {
            io.println("No reservations found.");
        }

        for (Guest g : guests) {
            io.printf("ID: %s || Name: %s %s || Email: %s ||" +
                            " State: %s || Phone: %s%n",
                    g.getId(),
                    g.getFirstName(),
                    g.getLastName(),
                    g.getEmail(),
                    g.getState(),
                    g.getPhone());

        }
    }
}
