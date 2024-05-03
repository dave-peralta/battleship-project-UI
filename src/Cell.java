public enum Cell {
    EMPTY("~"), MISS("*"), HIT("X"), CARRIER("C"), BATTLESHIP("B"), CRUISER("R"), SUBMARINE("S");

    private final String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isShip() {
        return this == CARRIER || this == BATTLESHIP || this == CRUISER || this == SUBMARINE;
    }

}
