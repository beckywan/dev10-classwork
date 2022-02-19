import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringMethodsTest {
    StringMethods instance = new StringMethods();

    @Test
    void startsWithDayOfWeekShouldBeFalseForEmptyArg() {
        assertFalse(instance.startsWithDayOfWeek(""));
    }

    @Test
    void startsWithDayOfWeekShouldBeFalseForNull() {
        assertFalse(instance.startsWithDayOfWeek(null));
    }

    @Test
    void startsWithDayOfWeekShouldDetectShort() {
        assertTrue(instance.startsWithDayOfWeek("Mon"));
    }

    @Test
    void startsWithDayOfWeekShouldFalseShort() {
        assertFalse(instance.startsWithDayOfWeek("Mo"));
    }

    @Test
    void startsWithDayOfWeekShouldDetect() {
        assertTrue(instance.startsWithDayOfWeek("Thurs"));
    }

    @Test
    void startsWithDayOfWeekShouldNotDetectLater() {
        assertFalse(instance.startsWithDayOfWeek("abTues"));
    }



    ///


    @Test
    void containsDayOfWeekShouldBeFalseForEmptyArg() {
        assertFalse(instance.containsDayOfWeek(""));
    }

    @Test
    void containsDayOfWeekShouldBeFalseForNull() {
        assertFalse(instance.containsDayOfWeek(null));
    }

    @Test
    void containsDayOfWeekShouldDetectShort() {
        assertTrue(instance.containsDayOfWeek("Mon"));
    }

    @Test
    void containsDayOfWeekShouldFalseShort() {
        assertFalse(instance.containsDayOfWeek("Mo"));
    }

    @Test
    void containsDayOfWeekShouldDetect() {
        assertTrue(instance.containsDayOfWeek("Thurs"));
    }

    @Test
    void containsDayOfWeekShouldNotDetectLater() {
        assertTrue(instance.containsDayOfWeek("abTues"));
    }

}