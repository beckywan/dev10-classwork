import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Exercise03Test {

    @Test
    void hasAllVowels() {
        assertFalse(Exercise03.hasAllVowels("ananjsdnks"));
        assertFalse(Exercise03.hasAllVowels("12414ds"));
        assertTrue(Exercise03.hasAllVowels("ananjsdeniOuks"));
        assertFalse(Exercise03.hasAllVowels("ananjeiUsdnks"));
    }
}