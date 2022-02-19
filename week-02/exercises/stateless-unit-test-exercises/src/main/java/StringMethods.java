public class StringMethods {
//    Name: startsWithDayOfWeek
//    Inputs: String
//    Output: boolean
//    Description: return true if the parameters starts with a day of week abbreviation:
//    Mon, Tues, Weds, Thurs, Fri, Sat, Sun
//    or false if it doesn't
    public boolean startsWithDayOfWeek (String input) {
    if (input == "" || input == null) {
        return false;
    }

    String[] weekAbbr = {"Mon", "Fri", "Sat", "Sun", "Tues", "Weds", "Thurs"};
    if (input.length() < 3) {
        return false;
    }

    if (input.length() == 3) {
        for (int i = 0; i < 4; i++) {
            String weekAbrStr = weekAbbr[i];

            String inputSubstring = input.substring(0, 3);

            if (inputSubstring.contains(weekAbrStr)) {
                return true;
            }
        }
    }

    if (input.length() == 4) {
        for (int i = 4; i < 6; i++) {
            String weekAbrStr = weekAbbr[i];

            String inputSubstring = input.substring(0, 4);

            if (inputSubstring.contains(weekAbrStr)) {
                return true;
            }
        }
    }

    for (int i = 0; i < weekAbbr.length; i++) {
        String weekAbrStr = weekAbbr[i];

        String inputSubstring = input.substring(0,5);

        if (inputSubstring.contains(weekAbrStr)) {
            return true;
        }
    }
    return false;
}

    public boolean containsDayOfWeek (String input) {
        if (input == "" || input == null) {
            return false;
        }

        String[] weekAbbr = {"Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun"};
        for (int i = 0; i < weekAbbr.length; i++) {
            String weekAbrStr = weekAbbr[i];
            if (input.contains(weekAbrStr)) {
                return true;
            }
        }
        return false;
    }


}
