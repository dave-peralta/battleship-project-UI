public class Board {
    public static final int SIZE = 10;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }
    }

    public boolean placeShip(Position position, int size, boolean horizontal, Cell shipType) {
        int row = position.row;
        int col = position.col;
        if (horizontal) {
            if (col + size > SIZE) return false;
            for (int i = col; i < col + size; i++) {
                if (cells[row][i] != Cell.EMPTY) return false;
            }
            for (int i = col; i < col + size; i++) {
                cells[row][i] = shipType;
            }
        } else {
            if (row + size > SIZE) return false;
            for (int i = row; i < row + size; i++) {
                if (cells[i][col] != Cell.EMPTY) return false;
            }
            for (int i = row; i < row + size; i++) {
                cells[i][col] = shipType;
            }
        }
        return true;
    }

    public boolean fireAt(Position position) {
        if (cells[position.row][position.col] != Cell.EMPTY && cells[position.row][position.col] != Cell.MISS && cells[position.row][position.col] != Cell.HIT) {
            cells[position.row][position.col] = Cell.HIT;
            return true;
        }
        cells[position.row][position.col] = Cell.MISS;
        return false;
    }

    public boolean hasRemainingShips() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == Cell.CARRIER || cells[i][j] == Cell.BATTLESHIP || cells[i][j] == Cell.CRUISER || cells[i][j] == Cell.SUBMARINE) {
                    return true;
                }
            }
        }
        return false;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean hasShipPlaced() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].isShip()) {
                    return true;
                }
            }
        }
        return false;
    }

}
