import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player(new Board());
        Player player2 = new Player(new Board());
        new GameWindow(player1, player2, "Battleship Game");
    }

}
