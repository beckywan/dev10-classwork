package learn.chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    Queen queen = new Queen();

    @Test
    void shouldMoveToFourCorners() {
        assertTrue(queen.move(7, 0)); // top left;
        assertEquals(7, queen.getRow());
        assertEquals(0, queen.getColumn());

        assertTrue(queen.move(7, 7)); // top right;
        assertEquals(7, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 7)); // bottom right;
        assertEquals(0, queen.getRow());
        assertEquals(7, queen.getColumn());

        assertTrue(queen.move(0, 0)); // bottom left;
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());
    }

    @Test
    void shouldNotMoveOffBoard() {
        // - anything off the board is invalid, should return false and leave field values alone.
        assertFalse(queen.move(8, 7));
        assertEquals(0, queen.getRow());
        assertEquals(0, queen.getColumn());

        // - should still be able to move after an invalid move.
        assertTrue(queen.move(5, 5));
        assertEquals(5, queen.getRow());
        assertEquals(5, queen.getColumn());


        assertTrue(queen.move(3, 5));
        assertEquals(3, queen.getRow());
        assertEquals(5, queen.getColumn());

        // - requesting the existing location should return false and leave field values alone.
        assertFalse(queen.move(3, 5));
        assertEquals(3, queen.getRow());
        assertEquals(5, queen.getColumn());

        // - can move diagonally in various ways
        assertTrue(queen.move(1, 3));
        assertEquals(1, queen.getRow());
        assertEquals(3, queen.getColumn());


        }
    }

    // 1. Add tests to validate Queen movement.
    // Required tests:




    // Always confirm that fields have been properly updated using getters.
