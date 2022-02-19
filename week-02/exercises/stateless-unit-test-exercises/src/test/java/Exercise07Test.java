import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise07Test {

    Exercise07 instance = new Exercise07();

    @Test
    void reverseOneElement() {
        String[] values = {"lower"};
        String[] expected = {"lower"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldBeEmptyForEmptyArg() {
        assertArrayEquals(new String[0], instance.reverse(new String[0]));
    }


    @Test
    void shouldBeNullForNulLArg() {
        String[] values = {null};
        String[] expected = {null};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldIgnoreNullElements() {
        String[] values = {"lower", null, "apple"};
        String[] expected = {"apple", null, "lower"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldIgnoreEmptyElements() {
        String[] values = {"lower", "", "apple"};
        String[] expected = {"apple", "", "lower"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldReverseMultipleElements() {
        String[] values = {"lower", "taller", "apple"};
        String[] expected = {"apple", "taller", "lower"};
        String[] actual = instance.reverse(values);
        assertArrayEquals(expected, actual);
    }
}