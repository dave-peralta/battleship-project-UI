import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    /**
     *
     * todo:
     *  1. Revise hard-coded values in Player Game Window
     *  2. Improve GUI Elements and Design
     *  3. General Error handling, especially during game setup
     *  4. Improve how turns are taken
     *  5. Add more documentation
     */
    private PlayerGameWindow player1Window, player2Window;
    private JLabel statusLabel;
    private int currentPlayer = 1; // Track the current player

    public GameWindow() {
        super("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLayout(new FlowLayout());

        statusLabel = new JLabel("Player 1's turn");
        add(statusLabel);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> startGame());
        add(startGameButton);

        setVisible(true);
    }

    private void startGame() {
        player1Window = new PlayerGameWindow();
        player2Window = new PlayerGameWindow();

        player1Window.setTitle("Player 1: Place your ships");
        player2Window.setTitle("Player 2: Place your ships");

        // Wait for both players to finish placing ships
        JButton finishSetupButton = new JButton("Finish Setup");
        finishSetupButton.addActionListener(e -> finishSetup());
        add(finishSetupButton);
        revalidate();
        repaint();
    }

    private void finishSetup() {
        // Assuming both players have finished setup
        getContentPane().removeAll();
        setLayout(new FlowLayout());
        add(statusLabel);

        JLabel xLabel = new JLabel("Enter X Coordinate: ");
        JTextField xField = new JTextField(5);
        JLabel yLabel = new JLabel("Enter Y Coordinate: ");
        JTextField yField = new JTextField(5);

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());

                if (currentPlayer == 1) {
                    if (attack(x, y, player2Window)) {
                        if (checkGameOver(player2Window)) {
                            gameEnd(1);
                            return;
                        }
                    }
                    currentPlayer = 2;
                } else {
                    if (attack(x, y, player1Window)) {
                        if (checkGameOver(player1Window)) {
                            gameEnd(2);
                            return;
                        }
                    }
                    currentPlayer = 1;
                }
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid coordinates", "Error", JOptionPane.ERROR_MESSAGE);
            }

            revalidate();
            repaint();
        });

        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(attackButton);
    }


    private boolean attack(int x, int y, PlayerGameWindow opponentWindow) {
        JButton targetButton = opponentWindow.getGridButton(x, y);
        String buttonText = targetButton.getText();
        if (!buttonText.equals("")) { // Assume empty buttons have no text
            targetButton.setBackground(Color.RED); // Hit
            targetButton.setEnabled(false); // Disable the button to prevent further attacks on this cell
            return true; // Hit
        } else {
            targetButton.setBackground(Color.BLUE); // Miss
            targetButton.setEnabled(false);
            return false; // Miss
        }
    }


    private boolean checkGameOver(PlayerGameWindow window) {
        for (JButton[] buttonRow : window.getGridButtons()) {
            for (JButton button : buttonRow) {
                if (!button.getText().isEmpty() && button.isEnabled()) { // Check if there are any ships that are not hit
                    return false; // Game not over
                }
            }
        }
        return true; // All ships hit
    }


    private void gameEnd(int winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
