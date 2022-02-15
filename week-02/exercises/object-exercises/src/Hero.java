public class Hero {
    public String name;
    private Power[] powers;

    public Hero(String name, Power[] powers) {
        this.name = name;
        this.powers = powers;
    }

    public String getName() {
        return name;
    }

    public Power[] getPowers() {
        return powers;
    }

    // Name: toLine
    // Inputs: none
    // Output: String
    // Description: returns the Hero's name and powers as a single line of text.
    public String toLine() {
        String result = name + ":";

        for(Power p : powers) {
            result += p.getName() + ", ";

        }
        return result.substring(0, result.lastIndexOf(','));
    }
}
