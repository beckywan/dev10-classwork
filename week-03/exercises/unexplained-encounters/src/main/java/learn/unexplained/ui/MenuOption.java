package learn.unexplained.ui;

public enum MenuOption {

    EXIT("Exit"),
    DISPLAY_ALL("Display All Encounters"),
    DISPLAY_BY_TYPE("Display By Type"),
    ADD("Add An Encounter"),
    UPDATE("Update An Encounter"),
    DElETE("Delete An Encounter");




    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}
