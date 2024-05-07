import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerGameWindow extends JFrame {
    private JPanel gridPanel;
    private JButton[][] gridButtons;
    private JButton horizontalButton, verticalButton;
    private JComboBox<String> shipSelection;
    private boolean isHorizontal = true;
    private int[][] shipSizes = { {4, 0}, {5, 0}, {3, 0}, {2, 0} }; // Sizes and placement counts
    private String[] shipTypes = { "CARRIER", "BATTLESHIP", "CRUISER", "SUBMARINE" };

    public PlayerGameWindow() {
        super("Battleship Game - Setup Ships");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridLayout(10, 10));
        gridButtons = new JButton[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> placeShip(finalI, finalJ));
                gridButtons[i][j] = button;
                gridPanel.add(button);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        horizontalButton = new JButton("Horizontal");
        verticalButton = new JButton("Vertical");
        horizontalButton.addActionListener(e -> isHorizontal = true);
        verticalButton.addActionListener(e -> isHorizontal = false);
        shipSelection = new JComboBox<>(shipTypes);

        controlPanel.add(horizontalButton);
        controlPanel.add(verticalButton);
        controlPanel.add(shipSelection);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Getter method for an individual grid button
    public JButton getGridButton(int x, int y) {
        return gridButtons[x][y];
    }

    // Getter method for the entire grid of buttons
    public JButton[][] getGridButtons() {
        return gridButtons;
    }

    private void placeShip(int x, int y) {
        int shipIndex = shipSelection.getSelectedIndex();
        int shipSize = shipSizes[shipIndex][0];

        if (shipSizes[shipIndex][1] == 0) { // Check if the ship hasn't been placed
            // Check if placement is valid
            if (isPlacementValid(x, y, shipSize, isHorizontal)) {
                for (int i = 0; i < shipSize; i++) {
                    int dx = x + (isHorizontal ? 0 : i);
                    int dy = y + (isHorizontal ? i : 0);
                    gridButtons[dx][dy].setText(shipTypes[shipIndex].substring(0, 1)); // Set ship initial
                }
                shipSizes[shipIndex][1] = 1; // Mark ship as placed
            } else {
                JOptionPane.showMessageDialog(this, "Invalid placement. Check bounds and overlaps.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "This ship has already been placed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isPlacementValid(int x, int y, int size, boolean isHorizontal) {
        for (int i = 0; i < size; i++) {
            int dx = x + (isHorizontal ? 0 : i);
            int dy = y + (isHorizontal ? i : 0);
            if (dx >= 10 || dy >= 10 || !gridButtons[dx][dy].getText().isEmpty()) { // Check bounds and overlap
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new PlayerGameWindow();
    }
}
