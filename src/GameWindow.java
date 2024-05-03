import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Todo:

 1. Separate Game Windows between Player 1 and Player 2
 2. Add option to place ships horizontally or vertically in GUI
 3. Prompts for players if it is their turn
 4. Display for each player if their move is a HIT or a MISS

 Other Possible Improvements:

 1. Follow SOLID principles
 2. Add more documentation
 3. GUI Design Improvements

 **/

public class GameWindow extends JFrame {
    private Player player1, player2;
    private int currentPlayer = 1;  // 1 for player1, 2 for player2
    private JButton[][] buttonsPlayer1 = new JButton[Board.SIZE][Board.SIZE];
    private JButton[][] buttonsPlayer2 = new JButton[Board.SIZE][Board.SIZE];
    private Board board1, board2;
    private boolean setupComplete = false;
    private Cell currentShipType = Cell.CARRIER;

    public GameWindow(Player player1, Player player2, String title) {
        this.player1 = player1;
        this.player2 = player2;
        this.board1 = player1.getBoard();
        this.board2 = player2.getBoard();
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));  // GridLayout to hold two boards
        initializeBoards();
        setSize(1000, 500);  // Adjust size for two boards
        setVisible(true);
    }

    private void initializeBoards() {
        JPanel panel1 = new JPanel(new GridLayout(Board.SIZE, Board.SIZE));
        JPanel panel2 = new JPanel(new GridLayout(Board.SIZE, Board.SIZE));
        createBoard(panel1, buttonsPlayer1, board1, 1);
        createBoard(panel2, buttonsPlayer2, board2, 2);
        add(panel1);
        add(panel2);
        setupShips();
    }

    private void createBoard(JPanel panel, JButton[][] buttons, Board board, int playerNumber) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                JButton button = new JButton();
                final int finalI = i;
                final int finalJ = j;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!setupComplete) {
                            placeShipGUI(finalI, finalJ, board, playerNumber);
                        } else if (currentPlayer == playerNumber) {
                            handleGameInteraction(finalI, finalJ, board, buttons);
                        }
                    }
                });
                buttons[i][j] = button;
                panel.add(button);
            }
        }
    }

    private void setupShips() {
        JOptionPane.showMessageDialog(this, "Player 1, set up your ships. Start with the Carrier.");
    }

    private void placeShipGUI(int row, int col, Board board, int playerNumber) {
        Cell shipType = switch (currentShipType) {
            case EMPTY, MISS, HIT -> null;
            case CARRIER -> Cell.CARRIER;
            case BATTLESHIP -> Cell.BATTLESHIP;
            case CRUISER -> Cell.CRUISER;
            case SUBMARINE -> Cell.SUBMARINE;
        };
        int size = switch (currentShipType) {
            case EMPTY, MISS, HIT -> 0;
            case CARRIER -> 5;
            case BATTLESHIP -> 4;
            case CRUISER -> 3;
            case SUBMARINE -> 2;
        };

        if (board.placeShip(new Position(row, col), size, true, shipType)) {
            updateButtonLabelsForShips(board, playerNumber == 1 ? buttonsPlayer1 : buttonsPlayer2);
            advanceShipType(playerNumber);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid placement, try again.");
        }
    }

    private void advanceShipType(int playerNumber) {
        if (currentShipType == Cell.SUBMARINE) {
            if (playerNumber == 1) {
                JOptionPane.showMessageDialog(this, "Player 1 setup complete. Player 2, set up your ships. Start with the Carrier.");
                currentPlayer = 2;
                currentShipType = Cell.CARRIER;
            } else {
                JOptionPane.showMessageDialog(this, "Setup complete. Game starts.");
                setupComplete = true;
                currentPlayer = 1; // Player 1 starts the game
            }
        } else {
            currentShipType = Cell.values()[currentShipType.ordinal() + 1];
            JOptionPane.showMessageDialog(this, "Place your " + currentShipType);
        }
    }

    private void updateButtonLabelsForShips(Board board, JButton[][] buttons) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                if (board.getCell(i, j).isShip()) {
                    buttons[i][j].setText(board.getCell(i, j).getSymbol());
                }
            }
        }
    }

    private void handleGameInteraction(int row, int col, Board board, JButton[][] buttons) {
        if (board.fireAt(new Position(row, col))) {
            buttons[row][col].setText("Hit");
        } else {
            buttons[row][col].setText("Miss");
        }
        currentPlayer = currentPlayer == 1 ? 2 : 1;
        checkWinCondition();
    }

    private void checkWinCondition() {
        if (!player1.getBoard().hasRemainingShips()) {
            JOptionPane.showMessageDialog(this, "Player 2 wins!");
        } else if (!player2.getBoard().hasRemainingShips()) {
            JOptionPane.showMessageDialog(this, "Player 1 wins!");
        }
    }

    public static void main(String[] args) {
        Player player1 = new Player(new Board());
        Player player2 = new Player(new Board());
        new GameWindow(player1, player2, "Battleship Game");
    }
}
