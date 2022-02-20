package learn.chess;

public class Knight {
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

        if (Math.abs(getRow() - row) == 2  && Math.abs(getColumn() - column) == 1){
            row1 = row;
            column1 = column;
            return true;
        } else if (Math.abs(getRow() - row) == 1  && Math.abs(getColumn() - column) == 2){
            row1 = row;
            column1 = column;
            return true;
        }

        return false;
    }
}

