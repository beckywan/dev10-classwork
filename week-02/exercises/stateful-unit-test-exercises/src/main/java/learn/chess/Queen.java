package learn.chess;

/**
 * The most powerful chess piece.
 * Moves horizontally, vertically, or diagonally as many spaces as she wants.
 * She tracks her current position with two integer fields: row and column.
 * Rows and columns are zero-based.
 * The queen starts at row 0 and column 0, though there is no board.
 * Row 7 - - - - - - - -
 * Row 6 - - - - - - - -
 * Row 5 - - - - - - - -
 * Row 4 - - - - - - - -
 * Row 3 - - - - - - - -
 * Row 2 - - - - - - - -
 * Row 1 - - - - - - - -
 * Row 0 Q - - - - - - -
 * Cols: 0 1 2 3 4 5 6 7
 * <p>
 * See: https://en.wikipedia.org/wiki/Queen_(chess)
 */
public class Queen {

    private int row1 = 0;
    private int column1 = 0;

    public int getRow() {
        return row1;
    }

    public int getColumn() {
        return column1;
    }

    /**
     * Requests a move to a new row and column.
     *
     * @param row    the requested row
     * @param column the requested column
     * @return true if the move is valid, false if it's not
     */
    public boolean move(int row, int column) {
        if (row == getRow() && column == getColumn()) {
            return false;
        }else  if (row < 0 || row > 7) {
            return false;
        }else if (column < 0 || column > 7) {
            return false;
        }

        if (Math.abs(getRow() - row) == Math.abs(getColumn() - column)){
            row1 = row;
            column1 = column;
            return true;
        } else if (getRow() == row && getColumn() != column){
                row1 = row;
                column1 = column;
                return true;
        }else if (getRow() != row && getColumn() == column){
                row1 = row;
                column1 = column;
                return true;
            }



            // 1. Implement the move method.
        // If the move is valid, return true and update `row` and `column` fields.
        // If the move is invalid, return false and do not update fields.
        // Rules for valid moves:
        // - row and column can never be less than 0 or greater than 7 (8 rows and columns on a chess board).
        // - can't equal the existing row and column (same location)
        // - If the row parameter and field are the same but the column is not, the queen is moving horizontally. That's valid.
        // - If the column parameter and field are the same but the row is not, the queen is moving vertically. That's valid.
        // - Otherwise, the absolute difference between row parameter and field
        //   and the absolute difference between the column parameter and field must be the same.
        //   That represents a diagonal move.
        return false;
    }
}
