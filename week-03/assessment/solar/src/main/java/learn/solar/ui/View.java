package learn.solar.ui;

import learn.solar.domain.PanelResult;
import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.sql.SQLOutput;
import java.util.*;

public class View {
    private Scanner console = new Scanner(System.in);

    public MenuOption displayMenuGetOption() {
        printHeader("Main Menu");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%s. %s%n", i + 1, options[i].getMessage());
        }

        String msg = String.format("Select [%s-%s]:", 1, options.length);
        int value = readInt(msg, 1, options.length);
        return options[value - 1];
    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }


    public void printBySection(List<Panel> panels) {
        printHeader(MenuOption.DISPLAY_BY_SECTION.getMessage());
        if (panels == null || panels.size() == 0) {
            System.out.println();
            System.out.println("No panels found.");
        } else {
            System.out.println();
            for (Panel panel : panels) {
                System.out.printf("ID: %s || Section: %s || Row: %s || Column: %s || Installation Year: %s || Material: %s || Tracking: %s%n",
                        panel.getId(),
                        panel.getSection(),
                        panel.getRow(),
                        panel.getColumn(),
                        panel.getInstallationYear(),
                        panel.getMaterial(),
                        panel.getTracking());
            }
        }
    }


    public void printResult(PanelResult result) {
        if (result.isSuccess()) {
            if (result.getPayload() != null) {
                System.out.println("Success!");
            }
        } else {
            printHeader("Errors");
            for (String msg : result.getMessages()) {
                System.out.printf("- %s%n", msg);
            }
        }
    }

    public Panel makePanel() {
        printHeader(MenuOption.ADD.getMessage());
        Panel panel = new Panel();
        panel.setSection(readRequiredString("Section:"));
        panel.setRow(readInt("Row:"));
        panel.setColumn(readInt("Column:"));
        panel.setInstallationYear(readInt("Installation Year:"));
        panel.setMaterial(readMaterial());
        panel.setTracking(readRequiredString("Tracking [yes/no]:"));
        return panel;
    }

    public int deletePanel(List<Panel> panels) {
        printBySection(panels);
        if (panels == null || panels.size() == 0) {
            System.out.println();
            System.out.println("No panels found.");
            return 0;
        }

        int panelID = readInt("Panel ID to delete: ");
        for (Panel e : panels) {
            if (e.getId() == panelID) {
                return panelID;
            }
        }
        return panelID;
    }

    public Panel updatePanel(List<Panel> panels) {
        printBySection(panels);
        if (panels == null || panels.size() == 0) {
            System.out.println();
            System.out.println("No panels found.");
            return null;
        }

        int panelID = readInt("Panel ID to update: ");
        for (Panel e : panels) {
            if (e.getId() == panelID) {
                return update(e);
            }
        }
        return null;
    }


    private Panel update(Panel panel) {
        Material material = readMaterial();
        if (material != null) {
            panel.setMaterial(material);
        }

        int year = readInt("Installation Year (" + panel.getInstallationYear() + "):", 0, 2022);
        if (year > 0) {
            panel.setInstallationYear(year);
        }

        String track = readString("Tracking (" + panel.getTracking() + "):");
        if (track.trim().length() > 0) {
            panel.setTracking(track);
        }

        return panel;
    }


    private String readString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    private String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    private int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String message, int min, int max) {
        int result;
        do {
            result = readInt(message);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    public Material readMaterial() {
        int index = 1;
        for (Material m : Material.values()) {
            System.out.printf("%s. %s%n", index++, m);
        }
        index--;
        String msg = String.format("Select Material Type [1-%s]:", index);
        return Material.values()[readInt(msg, 1, index) - 1];
    }

    public String readSection(List<Panel> all) {
        List<String> sections = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < all.size(); i++) {
            if (!sections.contains(all.get(i).getSection())) {
                sections.add(all.get(i).getSection());
            }
        }

        for (String s : sections) {
            System.out.println(s);
        }

        String result = readRequiredString("Choose a section:");
        return result;
    }

}
