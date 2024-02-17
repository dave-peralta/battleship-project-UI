public class Ship {
    private int size;
    private char letter;
    private char direction;

    private Coordinate coordinate;

    public Ship(){};

    public Ship(int size, char letter, Coordinate coordinate) {
        this.size = size;
        this.letter = letter;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }


}