import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise05Test {

    @Test
    void isWithinFiveOfAHundred() {
        Exercise05 instance = new Exercise05();

        assertTrue(instance.isWithinFiveOfAHundred(100));
        assertTrue(instance.isWithinFiveOfAHundred(205));
        assertTrue(instance.isWithinFiveOfAHundred(-95));
        assertTrue(instance.isWithinFiveOfAHundred(0));
        assertFalse(instance.isWithinFiveOfAHundred(92));
    }
}