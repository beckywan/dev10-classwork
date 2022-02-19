import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise02Test {

    @Test
    void surroundWithTags() {
        assertEquals("<b>a</b>", Exercise02.surroundWithTag("a", "b"));
        assertEquals("splendid", Exercise02.surroundWithTag("splendid", null));
        assertEquals("<abc></abc>", Exercise02.surroundWithTag(null, "abc"));
        assertEquals(null, Exercise02.surroundWithTag(null, null));
    }
}