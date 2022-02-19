import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise06Test {

    Exercise06 instance = new Exercise06();

    // Suggested test additions:
    // shouldBeNullForNulLArg
    // shouldCapitalizeMultipleElements (several scenarios)
    // shouldIgnoreNullElements
    // shouldIgnoreEmptyElements

    @Test
    void shouldCapitalizeOneElement() {
        String[] values = {"lower"};
        String[] expected = {"Lower"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeEmptyForEmptyArg() {
        assertArrayEquals(new String[0], instance.capitalizeAll(new String[0]));
    }


    @Test
    void shouldBeNullForNulLArg() {
        String[] values = {null};
        String[] expected = {null};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldIgnoreNullElements() {
        String[] values = {"lower", null, "apple"};
        String[] expected = {"Lower", null, "Apple"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldIgnoreEmptyElements() {
        String[] values = {"lower", "", "apple"};
        String[] expected = {"Lower", "", "Apple"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldCapitalizeMultipleElements() {
        String[] values = {"lower", "taller", "apple"};
        String[] expected = {"Lower", "Taller", "Apple"};
        String[] actual = instance.capitalizeAll(values);
        assertArrayEquals(expected, actual);
    }
}