package learn.reservation.ui;

public enum MenuOptions {
    VIEW_RESERVATIONS(0, "View Reservations"),
    ADD_RESERVATION(1, "Add a Reservation"),
    EDIT_RESERVATION(2,"Edit a Reservation"),
    DELETE_RESERVATION(3,"Delete a Reservation"),
    EXIT(4,"Exit");

    private int value;
    private String message;

    private MenuOptions(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static MenuOptions fromValue(int value) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
