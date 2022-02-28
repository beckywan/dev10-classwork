package learn.solar.ui;

public enum MenuOption {

    EXIT("Exit"),
    DISPLAY_BY_SECTION("Display By Section"),
    ADD("Add A Panel"),
    UPDATE("Update A Panel"),
    DElETE("Delete A Panel");


    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }

}
