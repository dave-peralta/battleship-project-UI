public class Boards {
    private char[][] boardA;
    private char[][] boardB;
    private int numDestroyer;
    private int numSubmarine;
    private int numCruiser;
    private int numBattleship;
    private int numCarrier;


    
    public char[][] getBoardA() {
        return boardA;
    }

    public void setBoardA(char[][] boardA) {
        this.boardA = boardA;
    }

    public char[][] getBoardB() {
        return boardB;
    }

    public void setBoardB(char[][] boardB) {
        this.boardB = boardB;
    }

    public int getNumSubmarine() {
        return numSubmarine;
    }

    public void setNumSubmarine(int numSubmarine) {
        this.numSubmarine = numSubmarine;
    }

    public int getNumCruiser() {
        return numCruiser;
    }

    public void setNumCruiser(int numCruiser) {
        this.numCruiser = numCruiser;
    }

    public int getNumBattleship() {
        return numBattleship;
    }

    public void setNumBattleship(int numBattleship) {
        this.numBattleship = numBattleship;
    }

    public int getNumCarrier() {
        return numCarrier;
    }

    public void setNumCarrier(int numCarrier) {
        this.numCarrier = numCarrier;
    }

    public void setNumDestroyer(int numDestroyer) {
        this.numDestroyer = numDestroyer;
    }

    public int getNumDestroyer() {
        return numDestroyer;
    }

}