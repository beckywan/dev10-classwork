package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Knight knight = new Knight();

    @Test
    void shouldNotMoveOffBoard() {
        // - anything off the board is invalid, should return false and leave field values alone.
        assertFalse(knight.move(-2, -1));
        assertEquals(0, knight.getRow());
        assertEquals(0, knight.getColumn());

        // - should still be able to move after an invalid move.
        assertTrue(knight.move(2, 1));
        assertEquals(2, knight.getRow());
        assertEquals(1, knight.getColumn());


        assertTrue(knight.move(3, 3));
        assertEquals(3, knight.getRow());
        assertEquals(3, knight.getColumn());

        // - requesting the existing location should return false and leave field values alone.
        assertFalse(knight.move(3, 3));
        assertEquals(3, knight.getRow());
        assertEquals(3, knight.getColumn());

        // - can move diagonally in various ways
        assertTrue(knight.move(1, 2));
        assertEquals(1, knight.getRow());
        assertEquals(2, knight.getColumn());


    }
}
