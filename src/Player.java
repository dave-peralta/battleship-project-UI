public class Player {
    private Board board;
    private String playerName;

    public Player(Board board, String playerName) {
        this.board = new Board();
        this.playerName = playerName;
    }

    public Board getBoard() {
        return board;
    }
}
