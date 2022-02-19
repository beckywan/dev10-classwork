import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class ArrayMethodsTest {

    ArrayMethods instance = new ArrayMethods();


    @Test
    void removeZero() {
        int[] values = {0};
        int[] expected = {};
        int[] actual = instance.removeZero(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeZeroMultiple() {
        int[] values = {0, 7, -8, 0, 9};
        int[] expected = {7, -8, 9};
        int[] actual = instance.removeZero(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeZeroEmptyForNull() {
        int[] values = null;
        int[] expected = {};
        int[] actual = instance.removeZero(values);
        assertArrayEquals(expected, actual);
    }

    @Test
    void removeZeroEmptyforEmpty() {
        int[] values = {};
        int[] expected = {};
        int[] actual = instance.removeZero(values);
        assertArrayEquals(expected, actual);
    }

}